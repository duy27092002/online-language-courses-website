package com.javaproject.admin.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.entity.Course;
import com.javaproject.admin.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO extends BaseDTO<VideoDTO> {
	private int index_;
	
	private String videoFile;
	MultipartFile videoFileName;
	
	@NotBlank(message = "Vui lòng nhập tên video!")
	@Length(min = 1, max = 255, message = "Tên video không được vượt quá 255 ký tự")
	private String name;
	
	private String thumbnail;
	MultipartFile thumbnailImg;
	
	@NotBlank(message = "Vui lòng nhập mô tả cho video!")
	private String description;
	
	@Length(max = 1000, message = "Nhận xét không được quá 1000 ký tự")
	private String cmt;
	
	private Long courseId;

	private Long userId;
	
	// response
	private Course course;
	
	private User user;
}
