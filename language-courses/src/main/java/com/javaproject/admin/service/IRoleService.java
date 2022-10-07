package com.javaproject.admin.service;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.RoleDTO;

public interface IRoleService extends IBaseService<RoleDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	RoleDTO getRoleByName(String name);
}
