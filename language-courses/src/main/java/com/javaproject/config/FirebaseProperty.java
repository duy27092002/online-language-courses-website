package com.javaproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperty {
	private String bucketName;

    private String imageUrl;
}
