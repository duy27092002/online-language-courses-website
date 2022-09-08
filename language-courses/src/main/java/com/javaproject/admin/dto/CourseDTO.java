package com.javaproject.admin.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

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

	@NotNull(message = "Không được để trống mô tả khóa học!")
	@NotBlank(message = "Vui lòng nhập mô tả khóa học!")
	private String description;

	@NotNull(message = "Không được để trống giá khóa học!")
	@Pattern(regexp = "^\\d{1,10}$", message = "Sai định dạng giá tiền!")
	@NotBlank(message = "Vui lòng nhập giá cho khóa học!")
	private int price;

	private int discount;

	private Date startDiscountTime;

	private Date endDiscountTime;

	private Date releaseTime;

	private Long languageId;
}
