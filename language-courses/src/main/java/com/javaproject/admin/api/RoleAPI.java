package com.javaproject.admin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.service.IRoleService;
import com.javaproject.admin.util.SortUtil;

@RestController(value = "roleAPIOfAdmin")
@RequestMapping(value = "/api/role")
public class RoleAPI {
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private SortUtil sortUtil;
	
	@GetMapping
	public ResponseEntity<RoleDTO> showRoleListPage(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "order-by", required = false, defaultValue = "name") String orderBy,
			@RequestParam(name = "order-type", required = false, defaultValue = "asc") String orderType,
			@RequestParam(name = "keyword", required = false) String keyword) {
		RoleDTO roleDTO = new RoleDTO();
		
		if (keyword != null) {
			
		} else {
			int pageSize = 2;
			roleDTO.setCurrentPage(page);
			roleDTO.setPageTotal(roleService.getTotalPage(pageSize));
			Pageable pageable = PageRequest.of(page - 1, pageSize, sortUtil.handleSord(orderBy, orderType));
			roleDTO.setResultList(roleService.getListByPage(pageable));
		}
		
		return ResponseEntity.ok(roleDTO);
	}
}
