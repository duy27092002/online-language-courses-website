package com.javaproject.admin.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean
	ModelMapper modelMapper() {
		// Tạo object và cấu hình
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD).setSkipNullEnabled(true)
				.setAmbiguityIgnored(true);

		return modelMapper;
	}
}
