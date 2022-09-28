package com.javaproject.admin.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class CourseDTO extends BaseDTO<CourseDTO> {
	@NotNull(message = "Không được để trống tên khóa học!")
	@NotBlank(message = "Vui lòng nhập tên khóa học!")
	@Length(min = 1, max = 255, message = "Tên khóa học không được vượt quá 255 ký tự")
	private String name;

	@NotNull(message = "Không được để trống ảnh đại diện khóa học!")
	private String thumbnail;
	MultipartFile thumbnailFile;

	@NotNull(message = "Không được để trống mô tả khóa học!")
	@NotBlank(message = "Vui lòng nhập mô tả khóa học!")
	private String description;

	@NotNull(message = "Không được để trống giá khóa học!")
	@Length(min = 1, max = 20, message = "Giá khóa học không được vượt quá 20 ký tự")
	@NotBlank(message = "Vui lòng nhập giá cho khóa học!")
	private String price;

	private int discount;

	private Date startDiscountTime;

	private Date endDiscountTime;

	private Date releaseTime;

	@NotNull(message = "Vui lòng chọn ngôn ngữ của khóa học!")
	private Long languageId;
	
	@NotEmpty(message = "Vui lòng chọn kỹ năng!")
	private Long[] skillLevelIds;
}
