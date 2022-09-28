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
public class EvaluatedDTO extends BaseDTO<EvaluatedDTO> {
	private Long courseId;
	
	private Long userId;
	
	@NotNull(message = "Không được để trống nội dung đánh giá!")
	@NotBlank(message = "Vui lòng nhập nội dung đánh giá!")
	@Length(min = 1, max = 1000, message = "Nội dung đánh giá không được vượt quá 1000 ký tự")
	private String content;
	
	@NotNull(message = "Không được để trống điểm đánh giá!")
	private int point;
}
