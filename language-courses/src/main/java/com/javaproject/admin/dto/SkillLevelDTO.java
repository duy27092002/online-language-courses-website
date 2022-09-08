package com.javaproject.admin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillLevelDTO extends BaseDTO<SkillLevelDTO> {
	@NotNull(message = "Không được để trống tên kỹ năng!")
	@NotBlank(message = "Vui lòng nhập tên kỹ năng!")
	@Length(min = 1, max = 20, message = "Tên kỹ năng không được vượt quá 20 ký tự")
	private String name;
}
