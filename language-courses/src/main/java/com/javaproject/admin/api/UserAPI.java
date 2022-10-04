package com.javaproject.admin.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.service.IUserService;
import com.javaproject.admin.util.SortUtil;

@RestController(value = "userControllerOfAdmin")
@RequestMapping(value = "/api/admin/user")
@RolesAllowed("ROLE_admin")
public class UserAPI {
	@Autowired
	private IUserService userService;

	@Autowired
	private SortUtil sortUtil;

	@GetMapping
	public ResponseEntity<?> showList(
			@Pattern(regexp = "^.+$") @RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam(name = "order-by", required = false, defaultValue = "name") String orderBy,
			@RequestParam(name = "order-type", required = false, defaultValue = "asc") String orderType,
			@RequestParam(name = "keyword", required = false) String keyword) {
		UserDTO userDTO = new UserDTO();
		try {
			int getPage = Integer.parseInt(page);
			int pageSize = 2;
			userDTO.setCurrentPage(getPage);
			userDTO.setPageTotal(userService.getTotalPage(pageSize));
			Pageable pageable = PageRequest.of(getPage - 1, pageSize, sortUtil.handleSort(orderBy, orderType));
			if (keyword != null) {
				userDTO.setResultList(userService.getList(keyword, pageable));
			} else if (keyword == null) {
				userDTO.setResultList(userService.getList(null, pageable));
			}
			return ResponseEntity.ok(userDTO);
		} catch (Exception ex) {
			throw new BadRequestException("Yêu cầu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> details(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(userService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> createNewUser(@Valid @RequestBody UserDTO dto) {
		UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(dto.getEmail(), null);
		if (getUserByEmail != null) {
			throw new BadRequestException("Email này đã tồn tại. Vui lòng thử lại!");
		}

		UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null, dto.getPhoneNumber());
		if (getUserByPhoneNumber != null) {
			throw new BadRequestException("Số điện thoại này đã tồn tại. Vui lòng thử lại!");
		}

		try {
			return ResponseEntity.ok(userService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	@RolesAllowed({ "ROLE_giang-vien", "ROLE_admin" })
	public ResponseEntity<?> update(@Valid @RequestBody UserDTO dto) {
		UserDTO getOldInfo = userService.getDetails(dto.getId()).get(0);

		if (!getOldInfo.getEmail().equalsIgnoreCase(dto.getEmail())) {
			UserDTO getUserByEmail = userService.getUserByEmailOrByPhoneNumber(dto.getEmail(), null);
			if (getUserByEmail != null) {
				throw new BadRequestException("Email này đã tồn tại. Vui lòng thử lại!");
			}
		}

		if (!getOldInfo.getPhoneNumber().equalsIgnoreCase(dto.getPhoneNumber())) {
			UserDTO getUserByPhoneNumber = userService.getUserByEmailOrByPhoneNumber(null, dto.getPhoneNumber());
			if (getUserByPhoneNumber != null) {
				throw new BadRequestException("Số điện thoại này đã tồn tại. Vui lòng thử lại!");
			}
		}

		try {
			return ResponseEntity.ok(userService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
