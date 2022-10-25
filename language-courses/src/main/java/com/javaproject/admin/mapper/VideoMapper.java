package com.javaproject.admin.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaproject.admin.dto.VideoDTO;
import com.javaproject.admin.entity.Video;

@Component
public class VideoMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public VideoDTO toDTO(Video entity) {
		modelMapper.typeMap(Video.class, VideoDTO.class).addMappings(v -> {
//			v.map(src -> src.getCourse(), VideoDTO::setCourseInfo);
//			v.map(src -> src.getUser(), VideoDTO::setInstructorInfo);
		});
		
		return modelMapper.map(entity, VideoDTO.class);
	}
}
