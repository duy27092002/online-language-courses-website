package com.javaproject.admin.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.entity.Role;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.mapper.UserMapper;
import com.javaproject.admin.repository.RoleRepository;
import com.javaproject.admin.repository.UserRepository;
import com.javaproject.admin.service.IUserService;
import com.javaproject.service.IImageService;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private IImageService imageService;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> getList(String keyword, Pageable pageable) {
		List<UserDTO> resultList = new ArrayList<>();
		List<User> entityList = null;
		if (keyword == null) {
			entityList = userRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			entityList = userRepo.getSearchList(keyword, pageable);
		}
		for (User user : entityList) {
			resultList.add(userMapper.toDTO(user));
		}
		return resultList;
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		Role getRoleById = roleRepo.findById(userDTO.getRoleId()).get();
		Long getUserId = userDTO.getId();
		User userEntity = null;
		PasswordEncoder password = new BCryptPasswordEncoder();
		String pass = password.encode(userDTO.getPassword());
		if (getUserId == null) {
			userEntity = new User();
		} else {
			userEntity = userRepo.findById(getUserId).get();
		}
		try {
			userDTO.setAvatar(getImageURL(userDTO.getFileImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setPassword(pass);
		userEntity.setRole(getRoleById);
		BeanUtils.copyProperties(userRepo.save(userEntity), userDTO);
		return userDTO;
	}

	@Override
	public List<UserDTO> getDetails(Long id) {
		User getUserById = userRepo.findById(id).get();
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(getUserById, userDTO);
		List<UserDTO> resultList = new ArrayList<>();
		resultList.add(userDTO);
		return resultList;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getCountOfRecord = (int) userRepo.count();
		return getCountOfRecord % pageSize == 0 ? getCountOfRecord / pageSize : ((getCountOfRecord / pageSize) + 1);
	}

	@Override
	public UserDTO getUserByEmailOrByPhoneNumber(String email, String phoneNumber) {
		User getUser = null;
		if (email != null) {
			getUser = userRepo.findByEmail(email);
		} else if (phoneNumber != null) {
			getUser = userRepo.findByPhoneNumber(phoneNumber);
		}
		if (getUser != null) {
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(getUser, userDTO);
			return userDTO;
		}
		return null;
	}
	
	private String getImageURL(MultipartFile files) throws IOException {
		String fileName = imageService.save(files);
        String imageUrl = imageService.getImageUrl(fileName);

        return imageUrl;
	}

}
