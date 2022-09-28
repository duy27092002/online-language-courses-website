package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.Evaluated;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.repository.CourseRepository;
import com.javaproject.admin.repository.EvaluatedRepository;
import com.javaproject.admin.repository.UserRepository;
import com.javaproject.admin.service.IEvaluatedService;

@Service
@Transactional
public class EvaluatedService implements IEvaluatedService {
	@Autowired
	private EvaluatedRepository evaluatedRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<EvaluatedDTO> getList(String keyword, Pageable pageable) {
		List<Evaluated> getList = null;
		if (keyword == null) {
			getList = evaluatedRepo.findAll(pageable).getContent();
		} else if (keyword != null && keyword.length() > 0) {
			getList = evaluatedRepo.getSearchListByKeyword(keyword, pageable);
		}
		List<EvaluatedDTO> resultList = new ArrayList<>();
		for (Evaluated item : getList) {
			EvaluatedDTO dto = new EvaluatedDTO();
			BeanUtils.copyProperties(item, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<EvaluatedDTO> getDetails(Long id) {
		Evaluated getEvaluatedById = evaluatedRepo.findById(id).get();
		List<EvaluatedDTO> getInfo = new ArrayList<>();
		EvaluatedDTO dto = new EvaluatedDTO();
		BeanUtils.copyProperties(getEvaluatedById, dto);
		getInfo.add(dto);
		return getInfo;
	}

	@Override
	public EvaluatedDTO save(EvaluatedDTO dto) {
		Long getEvaluatedId = dto.getId();
		Evaluated evaluatedEntity = null;
		if (getEvaluatedId == null) {
			evaluatedEntity = new Evaluated();
		} else {
			evaluatedEntity = evaluatedRepo.findById(getEvaluatedId).get();
		}
		Course getCourseById = courseRepo.findById(dto.getCourseId()).get();
		User getUserById = userRepo.findById(dto.getUserId()).get();
		evaluatedEntity.setCourse(getCourseById);
		evaluatedEntity.setUser(getUserById);
		BeanUtils.copyProperties(dto, evaluatedEntity);
		BeanUtils.copyProperties(evaluatedRepo.save(evaluatedEntity), dto);
		return dto;
	}

	@Override
	public int getTotalPage(int pageSize) {
		int getTotalItem = (int) evaluatedRepo.count();
		return (getTotalItem % pageSize == 0) ? (getTotalItem / pageSize) : ((getTotalItem / pageSize) + 1);
	}

	@Override
	public EvaluatedDTO getEvaluatedByUserIdAndCourseId(Long userId, Long courseId) {
		Evaluated getEvaluated = evaluatedRepo.findByUserIdAndCourseId(userId, courseId);
		if (getEvaluated != null) {
			EvaluatedDTO dto = new EvaluatedDTO();
			BeanUtils.copyProperties(getEvaluated, dto);
			return dto;
		}
		return null;
	}

}
