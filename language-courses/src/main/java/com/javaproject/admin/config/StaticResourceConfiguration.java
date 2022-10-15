package com.javaproject.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javaproject.util.SaveLocalFile;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image-file/**").addResourceLocations("file:/" + SaveLocalFile.FOLDER_IMAGE);
	}

}
