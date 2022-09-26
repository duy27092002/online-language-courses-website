package com.javaproject.admin.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.javaproject.admin.dto.UserDTO;

public interface IUserService {
	List<UserDTO> getList(String keyword, Pageable pageable);

	UserDTO save(UserDTO userDTO);

	List<UserDTO> getDetails(Long id);

	int getTotalPage(int pageSize);
	
	UserDTO getUserByEmailOrByPhoneNumber(String email, String phoneNumber);
}
