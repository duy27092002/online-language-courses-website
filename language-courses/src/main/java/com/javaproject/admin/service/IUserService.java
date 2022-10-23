package com.javaproject.admin.service;

import java.util.List;

import com.javaproject.admin.dto.ChangePasswordDTO;
import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.NotificationResponseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.UserDTO;

public interface IUserService extends IBaseService<UserDTO> {
	ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception;
	
	UserDTO getUserByEmailOrByPhoneNumber(String email, String phoneNumber);
	
	UserDTO update(UserDTO userDTO);
	
	NotificationResponseDTO changePassword(ChangePasswordDTO cpDTO);
	
	List<UserDTO> getListByRoleIdAndStatus(long roleId, int status);
	
	List<Long> getInstructorIdListByCourse(CourseDTO courseDTO);
}
