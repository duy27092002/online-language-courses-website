package com.javaproject.admin.service;

import com.javaproject.admin.dto.RoleDTO;

public interface IRoleService extends IBaseService<RoleDTO> {
	RoleDTO getRoleByName(String name);
}
