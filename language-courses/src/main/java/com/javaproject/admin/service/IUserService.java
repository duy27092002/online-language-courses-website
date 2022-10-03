package com.javaproject.admin.service;

import com.javaproject.admin.dto.UserDTO;

public interface IUserService extends IBaseService<UserDTO> {	
	UserDTO getUserByEmailOrByPhoneNumber(String email, String phoneNumber);
}
