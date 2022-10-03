package com.javaproject.admin.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.entity.User;

@Component
public class UserMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO toDTO(User entity) {
		modelMapper.typeMap(User.class, UserDTO.class).addMappings(u -> {
			u.map(src -> src.getRole().getName(), UserDTO::setRoleName);
		});
		
		return modelMapper.map(entity, UserDTO.class);
	}
}
