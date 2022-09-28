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

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.service.IEvaluatedService;
import com.javaproject.admin.util.SortUtil;

@RestController
@RequestMapping(value = "/api/evaluated")
public class EvaluatedAPI {
	@Autowired
	private IEvaluatedService evaluatedService;

	@Autowired
	private SortUtil sortUtil;

	@GetMapping
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> viewList(
			@Pattern(regexp = "^.+$") @RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam(name = "order-by", required = false, defaultValue = "point") String orderBy,
			@RequestParam(name = "order-type", required = false, defaultValue = "asc") String orderType,
			@RequestParam(name = "keyword", required = false) String keyword) {
		EvaluatedDTO evaluatedDTO = new EvaluatedDTO();
		try {
			int getPage = Integer.parseInt(page);
			int pageSize = 2;
			evaluatedDTO.setCurrentPage(getPage);
			evaluatedDTO.setPageTotal(evaluatedService.getTotalPage(pageSize));
			Pageable pageable = PageRequest.of(getPage - 1, pageSize, sortUtil.handleSort(orderBy, orderType));
			if (keyword != null) {
				evaluatedDTO.setResultList(evaluatedService.getList(keyword, pageable));
			} else if (keyword == null) {
				evaluatedDTO.setResultList(evaluatedService.getList(null, pageable));
			}
			return ResponseEntity.ok(evaluatedDTO);
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

	@GetMapping(value = "/{id}")
	@RolesAllowed("ROLE_admin")
	public ResponseEntity<?> getDetails(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(evaluatedService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	@RolesAllowed("ROLE_hoc-vien")
	public ResponseEntity<?> create(@Valid @RequestBody EvaluatedDTO dto) {
		EvaluatedDTO getEvaluatedByUserIdAndCourseId = evaluatedService.getEvaluatedByUserIdAndCourseId(dto.getUserId(),
				dto.getCourseId());
		if (getEvaluatedByUserIdAndCourseId != null) {
			throw new BadRequestException("Bạn đã đánh giá khóa học này rồi!");
		}

		try {
			return ResponseEntity.ok(evaluatedService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	@RolesAllowed({ "ROLE_hoc-vien", "ROLE_admin" })
	public ResponseEntity<?> update(@Valid @RequestBody EvaluatedDTO dto) {
		try {
			return ResponseEntity.ok(evaluatedService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
