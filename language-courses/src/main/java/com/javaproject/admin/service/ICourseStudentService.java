package com.javaproject.admin.service;

import java.util.List;

public interface ICourseStudentService {
	List<Long> getCourseIdListByUserId(Long userId);
}
