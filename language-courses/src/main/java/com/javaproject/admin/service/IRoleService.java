package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.RoleDTO;

public interface IRoleService {
	List<RoleDTO> getListByPage(Pageable pageable);
	
	List<RoleDTO> getSearchListByPage(String keyword, Pageable pageable);
	
	RoleDTO save(RoleDTO role);
	
	int getTotalPage(int pageSize);
}
