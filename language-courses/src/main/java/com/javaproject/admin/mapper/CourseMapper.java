package com.javaproject.admin.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaproject.admin.dto.CourseDTO;
import com.javaproject.admin.entity.Course;

@Component
public class CourseMapper {
	@Autowired
	private ModelMapper modelMapper;

	public CourseDTO toDTO(Course entity) {
		modelMapper.typeMap(Course.class, CourseDTO.class).addMappings(mapper -> {
//			mapper.map(src -> src.getLanguage().getName(), CourseDTO::setLanguageName);
//			mapper.map(src -> src.getSkillLevelList(), CourseDTO::setSkillLevelList);
		});

		return modelMapper.map(entity, CourseDTO.class);
	}
}
