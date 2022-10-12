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

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.paging.PagingParam;
import com.javaproject.admin.service.ICourseService;

@RestController(value = "courseControllerOfAdmin")
@RequestMapping(value = "/api/admin/course")
@RolesAllowed("ROLE_admin")
public class CourseAPI {
	@Autowired
	private ICourseService courseService;

//	@Autowired
//	private SortUtil sortUtil;
	
	@GetMapping
	public ResponseEntity<?> viewList(@PagingParam(path = "course") ResponseDataTableDTO resDTDTO) {
		try {
			return ResponseEntity.ok(courseService.getList(resDTDTO));
		} catch (Exception exp) {
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}

//	@GetMapping
//	public ResponseEntity<?> viewList(
//			@Pattern(regexp = "^.+$") @RequestParam(value = "page", required = false, defaultValue = "1") String page,
//			@RequestParam(name = "order-by", required = false, defaultValue = "name") String orderBy,
//			@RequestParam(name = "order-type", required = false, defaultValue = "asc") String orderType,
//			@RequestParam(name = "keyword", required = false) String keyword) {
//		CourseDTO courseDTO = new CourseDTO();
//		try {
//			int getPage = Integer.parseInt(page);
//			int pageSize = 2;
//			courseDTO.setCurrentPage(getPage);
//			courseDTO.setPageTotal(courseService.getTotalPage(pageSize));
//			Pageable pageable = PageRequest.of(getPage - 1, pageSize, sortUtil.handleSort(orderBy, orderType));
//			if (keyword != null) {
//				courseDTO.setResultList(courseService.getList(keyword, pageable));
//			} else if (keyword == null) {
//				courseDTO.setResultList(courseService.getList(null, pageable));
//			}
//			return ResponseEntity.ok(courseDTO);
//		} catch (Exception exp) {
//			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
//		}
//	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> viewDetails(@Pattern(regexp = "^.+$") @PathVariable String id) {
		try {
			Long getId = Long.parseLong(id);
			return ResponseEntity.ok(courseService.getDetails(getId));
		} catch (Exception ex) {
			throw new BadRequestException("Không tìm thấy dữ liệu. Vui lòng thử lại!");
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CourseDTO dto) {
		CourseDTO getCourseByName = courseService.getCourseByName(dto.getName());
		if (getCourseByName != null) {
			throw new BadRequestException("Tên khóa học này đã tồn tại. Vui lòng thử lại!");
		}
		
		try {
			return ResponseEntity.ok(courseService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody CourseDTO dto) {
		CourseDTO getOldInfo = courseService.getDetails(dto.getId()).get(0);
		if (!getOldInfo.getName().equalsIgnoreCase(dto.getName())) {
			CourseDTO getCourseByName = courseService.getCourseByName(dto.getName());
			if (getCourseByName != null) {
				throw new BadRequestException("Tên khóa học này đã tồn tại. Vui lòng thử lại!");
			}
		}
		
		try {
			return ResponseEntity.ok(courseService.save(dto));
		} catch (Exception ex) {
			throw new BadRequestException("Oops! Đã xảy ra lỗi máy chủ. Vui lòng thử lại!");
		}
	}
}
