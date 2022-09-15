package com.javaproject.admin.api;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.exception.BadRequestException;
import com.javaproject.admin.service.IRoleService;
import com.javaproject.admin.util.SortUtil;

@RestController(value = "roleAPIOfAdmin")
@RequestMapping(value = "/api/admin/role")
public class RoleAPI {
	@Autowired
	private IRoleService roleService;

	@Autowired
	private SortUtil sortUtil;

	@GetMapping
	@RolesAllowed("ROLE_ADMIN")
	public ResponseEntity<RoleDTO> showRoleListPage(
			@Pattern(regexp = "^.+$") @RequestParam(value = "page", required = false, defaultValue = "1") String page,
			@RequestParam(name = "order-by", required = false, defaultValue = "name") String orderBy,
			@RequestParam(name = "order-type", required = false, defaultValue = "asc") String orderType,
			@RequestParam(name = "keyword", required = false) String keyword) {
		RoleDTO roleDTO = new RoleDTO();
		try {
			int getPage = Integer.parseInt(page);
			int pageSize = 2;
			roleDTO.setCurrentPage(getPage);
			roleDTO.setPageTotal(roleService.getTotalPage(pageSize));
			Pageable pageable = PageRequest.of(getPage - 1, pageSize, sortUtil.handleSord(orderBy, orderType));
			if (keyword != null) {
				roleDTO.setResultList(roleService.getList(keyword, pageable));
			} else {
				roleDTO.setResultList(roleService.getList(null, pageable));
			}
			return ResponseEntity.ok(roleDTO);
		} catch (Exception exp) {
			
			throw new BadRequestException("Yêu câu không hợp lệ. Vui lòng kiểm tra lại!");
		}
	}
}
