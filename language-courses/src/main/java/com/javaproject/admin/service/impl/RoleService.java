package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.entity.Role;
import com.javaproject.admin.repository.RoleRepository;
import com.javaproject.admin.service.IRoleService;

@Service
@Transactional
public class RoleService implements IRoleService {
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(roleRepo, new RoleDTO().getClass(), 
				responseDataTableDTO.getKeyword());
	}

	@Override
	public List<RoleDTO> getList(String keyword, Pageable pageable) {
		return null;
	}

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		Long getRoleId = roleDTO.getId();
		Role roleEntity = null;
		if (getRoleId == null) {
			roleEntity = new Role();
		} else {
			roleEntity = roleRepo.findById(getRoleId).get();
		}
		BeanUtils.copyProperties(roleDTO, roleEntity);
		BeanUtils.copyProperties(roleRepo.save(roleEntity), roleDTO);
		return roleDTO;
	}

	@Override
	public List<RoleDTO> getDetails(Long id) {
		Role getRoleEntityById = roleRepo.findById(id).get();
		List<RoleDTO> roleDTO = new ArrayList<>();
		RoleDTO dto = new RoleDTO();
		BeanUtils.copyProperties(getRoleEntityById, dto);
		roleDTO.add(dto);
		return roleDTO;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) roleRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public RoleDTO getRoleByName(String name) {
		Role getRoleByName = roleRepo.findByName(name);
		if (getRoleByName != null) {
			RoleDTO dto = new RoleDTO();
			BeanUtils.copyProperties(getRoleByName, dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<RoleDTO> activeRoleList() {
		List<Role> getRoleListByStatus = roleRepo.findByStatus(1);
		List<RoleDTO> resultList = new ArrayList<>();
		for (Role role : getRoleListByStatus) {
			RoleDTO dto = new RoleDTO();
			BeanUtils.copyProperties(role, dto);
			resultList.add(dto);
		}
		return resultList;
	}
}
