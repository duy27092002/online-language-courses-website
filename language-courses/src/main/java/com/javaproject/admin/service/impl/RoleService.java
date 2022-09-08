package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaproject.admin.dto.RoleDTO;
import com.javaproject.admin.entity.Role;
import com.javaproject.admin.repository.RoleRepository;
import com.javaproject.admin.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<RoleDTO> getListByPage(Pageable pageable) {
		List<Role> getList = roleRepo.findAll(pageable).getContent();
		List<RoleDTO> resultList = new ArrayList<>();
		for (Role role : getList) {
			RoleDTO roleDTO = new RoleDTO();
			BeanUtils.copyProperties(role, roleDTO);
			resultList.add(roleDTO);
		}
		return resultList;
	}

	@Override
	public List<RoleDTO> getSearchListByPage(String keyword, Pageable pageable) {

		return null;
	}

	@Override
	public RoleDTO save(RoleDTO role) {

		return null;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) roleRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

}
