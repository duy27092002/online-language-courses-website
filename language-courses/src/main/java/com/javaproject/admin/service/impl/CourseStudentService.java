package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.dto.CourseStudentDTO;
import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.CourseStudent;
import com.javaproject.admin.entity.User;
import com.javaproject.admin.repository.CourseRepository;
import com.javaproject.admin.repository.CourseStudentRepository;
import com.javaproject.admin.repository.UserRepository;
import com.javaproject.admin.service.ICourseStudentService;

@Service
@Transactional
public class CourseStudentService implements ICourseStudentService {
	@Autowired
	private CourseStudentRepository csRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Long> getCourseIdListByUserId(Long userId) {
		List<CourseStudent> courseStudentListByUserId = csRepo.findByStudentId(userId);
		List<Long> resultList = new ArrayList<>();
		for (CourseStudent entity : courseStudentListByUserId) {
			resultList.add(entity.getCourse().getId());
		}
		return resultList;
	}

	@Override
	public CourseStudentDTO save(CourseStudentDTO dto) {
		CourseStudent entity = new CourseStudent();
		BeanUtils.copyProperties(dto, entity);
		Course getCourseById = courseRepo.findById(dto.getCourseId()).get();
		entity.setCourse(getCourseById);
		User getStudentById = userRepo.findById(dto.getStudentId()).get();
		entity.setStudent(getStudentById);
		CourseStudentDTO csAfterSave = new CourseStudentDTO();
		BeanUtils.copyProperties(csRepo.save(entity), csAfterSave);
		return csAfterSave;
	}

	@Override
	public List<Long> getStudentIdByCourseID(Long courseId) {
		List<CourseStudent> getListOfStudentByCourseId = csRepo.findByCourseId(courseId);
		List<Long> resultList = new ArrayList<>();
		for (CourseStudent entity : getListOfStudentByCourseId) {
			resultList.add(entity.getStudent().getId());
		}
		return resultList;
	}

}
