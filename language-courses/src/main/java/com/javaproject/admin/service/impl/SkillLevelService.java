package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
import com.javaproject.admin.dto.SkillLevelDTO;
import com.javaproject.admin.entity.SkillLevel;
import com.javaproject.admin.repository.SkillLevelRepository;
import com.javaproject.admin.service.ISkillLevelService;

@Service
@Transactional
public class SkillLevelService implements ISkillLevelService {
	@Autowired
	private SkillLevelRepository skillLevelRepo;

	@Override
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(skillLevelRepo, new SkillLevelDTO().getClass(),
				responseDataTableDTO.getKeyword());
	}

	@Override
	public List<SkillLevelDTO> getList(String keyword, Pageable pageable) {
		return null;
	}

	@Override
	public SkillLevelDTO save(SkillLevelDTO dto) {
		Long getSkillLevelId = dto.getId();
		SkillLevel entity = null;
		if (getSkillLevelId == null) {
			entity = new SkillLevel();
		} else {
			entity = skillLevelRepo.findById(getSkillLevelId).get();
		}
		BeanUtils.copyProperties(dto, entity);
		BeanUtils.copyProperties(skillLevelRepo.save(entity), dto);
		return dto;
	}

	@Override
	public List<SkillLevelDTO> getDetails(Long id) {
		SkillLevel getEntityById = skillLevelRepo.findById(id).get();
		List<SkillLevelDTO> sklDTO = new ArrayList<>();
		SkillLevelDTO dto = new SkillLevelDTO();
		BeanUtils.copyProperties(getEntityById, dto);
		sklDTO.add(dto);
		return sklDTO;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) skillLevelRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public SkillLevelDTO getSkillLevelByName(String name) {
		SkillLevel getByName = skillLevelRepo.findByName(name);
		if (getByName != null) {
			SkillLevelDTO dto = new SkillLevelDTO();
			BeanUtils.copyProperties(getByName, dto);
			return dto;
		}
		return null;
	}

	@Override
	public List<SkillLevelDTO> getListByStatus(int status) {
		List<SkillLevel> sklEntityList = skillLevelRepo.findByStatus(status);
		List<SkillLevelDTO> resultList = new ArrayList<>();
		for (SkillLevel item : sklEntityList) {
			SkillLevelDTO dto = new SkillLevelDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<Long> getSklIdListByCourse(CourseDTO courseDTO) {
		List<SkillLevel> getInstructorListByCourse = courseDTO.getSkillLevelList();
		List<Long> sklIdListByCourse = new ArrayList<>();
		for (SkillLevel item : getInstructorListByCourse) {
			sklIdListByCourse.add(item.getId());
		}
		return sklIdListByCourse;
	}

}
