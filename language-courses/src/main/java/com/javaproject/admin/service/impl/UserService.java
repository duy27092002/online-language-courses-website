package com.javaproject.admin.service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.dto.AccountDetails;
import com.javaproject.admin.dto.ChangePasswordDTO;
import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.NotificationResponseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.UserDTO;
import com.javaproject.admin.entity.Role;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.mapper.UserMapper;
import com.javaproject.admin.repository.RoleRepository;
import com.javaproject.admin.repository.UserRepository;
import com.javaproject.admin.service.IUserService;
import com.javaproject.service.IImageService;
import com.javaproject.util.Constant;

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
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(userRepo, new UserDTO().getClass(), responseDataTableDTO.getKeyword());
	}

	@Override
	public List<UserDTO> getList(String keyword, Pageable pageable) {
		return null;
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
			if (userDTO.getFileImage().getOriginalFilename().isEmpty()) {
				userDTO.setAvatar(
						"https://firebasestorage.googleapis.com/v0/b/edukate-ce6f1.appspot.com/o/avatar_default.png?alt=media&token=4e800018-0c17-4041-9f58-2ea8f45186cb");
			} else {
				userDTO.setAvatar(getImageURL(userDTO.getFileImage()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start convert dob string to dob date
		String dobString = userDTO.getDob();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dobDate = null;
		try {
			dobDate = df.parse(dobString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userEntity.setDob(dobDate);
		// end convert

		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setPassword(pass);
		userEntity.setRole(getRoleById);
		BeanUtils.copyProperties(userRepo.save(userEntity), userDTO);
		return userDTO;
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		Role getRoleById = roleRepo.findById(userDTO.getRoleId()).get();
		User userEntity = userRepo.findById(userDTO.getId()).get();

		try {
			if (userDTO.getFileImage() != null && !userDTO.getFileImage().getOriginalFilename().isEmpty()) {
				userDTO.setAvatar(getImageURL(userDTO.getFileImage()));
			} else {
				userDTO.setAvatar(userEntity.getAvatar());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// start convert dob string to dob date
		String dobString = userDTO.getDob();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dobDate = null;
		try {
			dobDate = df.parse(dobString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userEntity.setDob(dobDate);
		// end convert

		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setRole(getRoleById);
		BeanUtils.copyProperties(userRepo.save(userEntity), userDTO);
		return userDTO;
	}

	@Override
	public List<UserDTO> getDetails(Long id) {
		User getUserById = userRepo.findById(id).get();
		List<UserDTO> resultList = new ArrayList<>();
		resultList.add(userMapper.toDTO(getUserById));
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

	@Override
	public NotificationResponseDTO changePassword(ChangePasswordDTO cpDTO) {
		User getUserById = userRepo.findById(
				((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId())
				.get();
		PasswordEncoder password = new BCryptPasswordEncoder();
		if (!password.matches(cpDTO.getOldPass(), getUserById.getPassword())) {
			return new NotificationResponseDTO(Constant.CODE_ERROR, "Mật khẩu không khớp");
		}
		getUserById.setPassword(password.encode(cpDTO.getNewPass()));
		userRepo.save(getUserById);
		return new NotificationResponseDTO(Constant.CODE_SUCCESS, "Đổi mật khẩu thành công!");
	}

	private String getImageURL(MultipartFile files) throws IOException {
		String fileName = imageService.save(files);
		String imageUrl = imageService.getImageUrl(fileName);

		return imageUrl;
	}

	@Override
	public List<UserDTO> getListByRoleIdAndStatus(long roleId, int status) {
		List<User> userEntityList = userRepo.findByRoleIdAndStatus(roleId, status);
		List<UserDTO> resultList = new ArrayList<>();
		for (User entity : userEntityList) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<Long> getInstructorIdListByCourse(CourseDTO courseDTO) {
		List<User> getInstructorListByCourse = courseDTO.getInstructors();
		List<Long> instructorIdListByCourse = new ArrayList<>();
		for (User item : getInstructorListByCourse) {
			instructorIdListByCourse.add(item.getId());
		}
		return instructorIdListByCourse;
	}

	@Override
	public int getTotalEmployeeNotInstructorByStatus(int status) {
		int totalActiveUser = userRepo.findByStatus(1).size();
		int totalInactiveUser = userRepo.findByStatus(0).size();
		int totalActiveInstructor = getListByRoleIdAndStatus(2, 1).size();
		int totalInactiveInstructor = getListByRoleIdAndStatus(2, 0).size();
		int totalActiveStudent = getListByRoleIdAndStatus(3, 1).size();
		int totalInactiveStudent = getListByRoleIdAndStatus(3, 0).size();
		int totalActiveEmployee = totalActiveUser - totalActiveInstructor - totalActiveStudent;
		int totalInactiveEmployee = totalInactiveUser - totalInactiveInstructor - totalInactiveStudent;
		return status == 1 ? totalActiveEmployee : totalInactiveEmployee;
	}

}
