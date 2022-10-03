package com.javaproject.admin.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaproject.admin.dto.EvaluatedDTO;
import com.javaproject.admin.entity.Evaluated;

@Component
public class EvaluatedMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public EvaluatedDTO toDTO(Evaluated entity) {
		modelMapper.typeMap(Evaluated.class, EvaluatedDTO.class).addMappings(e -> {
			e.map(src -> src.getCourse().getName(), EvaluatedDTO::setCourseName);
			e.map(src -> src.getUser().getEmail(), EvaluatedDTO::setEmailOfStudent);
		});
		
		return modelMapper.map(entity, EvaluatedDTO.class);
	}
}
