package com.javaproject.admin.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.dto.ResponseDataTableDTO;
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
	public ResponseDataTableDTO getList(ResponseDataTableDTO responseDataTableDTO) throws Exception {
		return responseDataTableDTO.getList(evaluatedRepo, new EvaluatedDTO().getClass(),
				responseDataTableDTO.getKeyword());
	}

	@Override
	public List<EvaluatedDTO> getList(String keyword, Pageable pageable) {
		return null;
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
		BeanUtils.copyProperties(dto, evaluatedEntity);
		Course getCourseById = courseRepo.findById(dto.getCourseId()).get();
		evaluatedEntity.setCourse(getCourseById);
		User getUserById = userRepo.findById(dto.getUserId()).get();
		evaluatedEntity.setUser(getUserById);
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

	@Override
	public List<EvaluatedDTO> getEvaluatedByStatus(int status) {
		List<Evaluated> getActiveList = evaluatedRepo.findByStatus(status);
		List<EvaluatedDTO> resultList = new ArrayList<>();
		for (Evaluated entity : getActiveList) {
			EvaluatedDTO dto = new EvaluatedDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public List<EvaluatedDTO> getEvaluatedListByCourseId(Long courseId) {
		List<Evaluated> getListByCourseId = evaluatedRepo.findByCourseId(courseId);
		List<EvaluatedDTO> resultList = new ArrayList<>();
		for (Evaluated entity : getListByCourseId) {
			EvaluatedDTO dto = new EvaluatedDTO();
			BeanUtils.copyProperties(entity, dto);
			resultList.add(dto);
		}
		return resultList;
	}

	@Override
	public double rating(Long courseId) {
		List<EvaluatedDTO> getListByCourseId = getEvaluatedListByCourseId(courseId);
		int getSize = getListByCourseId.size();
		int sumOfPoint = 0;
		for (EvaluatedDTO evaluated : getListByCourseId) {
			sumOfPoint += evaluated.getPoint();
		}
		DecimalFormat numberFormat = new DecimalFormat("#.#");
		return Double.parseDouble(numberFormat.format(sumOfPoint * 1.0 / getSize));
	}

}
