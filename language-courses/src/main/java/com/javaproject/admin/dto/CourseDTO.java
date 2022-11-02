package com.javaproject.admin.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.entity.Language;
import com.javaproject.admin.entity.SkillLevel;
import com.javaproject.admin.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO extends BaseDTO<CourseDTO> {
	@NotBlank(message = "Vui lòng nhập tên khóa học!")
	@Length(min = 1, max = 255, message = "Tên khóa học không được vượt quá 255 ký tự")
	private String name;

	private String thumbnail;
	MultipartFile thumbnailFile;

	@NotBlank(message = "Vui lòng nhập mô tả khóa học!")
	private String description;

	@Length(min = 1, max = 20, message = "Giá khóa học không được vượt quá 20 ký tự")
	@NotBlank(message = "Vui lòng nhập giá cho khóa học!")
	private String price;

	private int discount;

	private Date startDiscountTime;
	private String startDisTimeStr;

	private Date endDiscountTime;
	private String endDisTimeStr;

	private Date releaseTime;
	private String releaseTimeStr;

	private Long languageId;
	
	@NotEmpty(message = "Vui lòng chọn kỹ năng!")
	private Long[] skillLevelIds;
	
	@NotEmpty(message = "Vui lòng chọn giảng viên phụ trách!")
	private Long[] instructorIds;
	
	// response
	private Language language;

	private List<SkillLevel> skillLevelList = new ArrayList<>();
	
	private List<User> instructors = new ArrayList<>();
	
	private boolean wasEvaluated;
}
