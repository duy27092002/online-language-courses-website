package com.javaproject.admin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO extends BaseDTO<VideoDTO> {
	@NotNull(message = "Vui lòng chọn video!")
	private String videoFile;
	MultipartFile videoName;
	
	@NotNull(message = "Không được để trống tên video!")
	@NotBlank(message = "Vui lòng nhập tên video!")
	@Length(min = 1, max = 255, message = "Tên video không được vượt quá 255 ký tự")
	private String name;
	
	@NotNull(message = "Vui lòng chọn hình đại diện cho video!")
	private String thumbnail;
	MultipartFile thumbnailImg;
	
	@NotNull(message = "Không được để mô tả video!")
	@NotBlank(message = "Vui lòng nhập mô tả cho video!")
	private String description;
	
	private Long courseId;

	private Long userId;
}
