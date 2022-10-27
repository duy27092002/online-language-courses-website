package com.javaproject.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaproject.admin.entity.CourseStudent;
import com.javaproject.admin.repository.CourseStudentRepository;
import com.javaproject.admin.service.ICourseStudentService;

@Service
@Transactional
public class CourseStudentService implements ICourseStudentService {
	@Autowired
	private CourseStudentRepository csRepo;

	@Override
	public List<Long> getCourseIdListByUserId(Long userId) {
		List<CourseStudent> courseStudentListByUserId = csRepo.findByStudentId(userId);
		List<Long> resultList = new ArrayList<>();
		for (CourseStudent entity : courseStudentListByUserId) {
			resultList.add(entity.getId());
		}
		return resultList;
	}

}
