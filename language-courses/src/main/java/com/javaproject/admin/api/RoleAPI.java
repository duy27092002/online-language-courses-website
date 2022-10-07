package com.javaproject.admin.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.IRoleService;

@RestController(value = "roleAPIOfAdmin")
@RequestMapping(value = "/api/admin/role")
@RolesAllowed("ROLE_admin")
public class RoleAPI {
	@Autowired
	private IRoleService roleService;
	
	@GetMapping
	public ResponseEntity<?> viewList(@PagingParam(path = "role") ResponseDataTableDTO resDTDTO) {
		try {
			return ResponseEntity.ok(roleService.getList(resDTDTO));
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> details(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(roleService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody RoleDTO dto) {
		RoleDTO getRoleByName = roleService.getRoleByName(dto.getName());
		if (getRoleByName != null) {
			throw new BadRequestException("Tên vai trò này đã tồn tại. Vui lòng thử lại!");
		}

		try {
			return ResponseEntity.ok(roleService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody RoleDTO dto) {
		RoleDTO getOldInfo = roleService.getDetails(dto.getId()).get(0);
		if (!getOldInfo.getName().equalsIgnoreCase(dto.getName())) {
			RoleDTO getRoleByName = roleService.getRoleByName(dto.getName());
			if (getRoleByName != null) {
				throw new BadRequestException("Tên vai trò này đã tồn tại. Vui lòng thử lại!");
			}
		}

		try {
			return ResponseEntity.ok(roleService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
