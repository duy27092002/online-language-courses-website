package com.javaproject.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseStudentDTO extends BaseDTO<CourseStudentDTO> {
	private Long courseId;
	
	private Long studentId;
}
