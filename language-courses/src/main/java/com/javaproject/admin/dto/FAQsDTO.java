package com.javaproject.admin.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQsDTO extends BaseDTO<FAQsDTO> {
	@NotBlank(message = "Vui lòng nhập câu hỏi!")
	@Length(min = 1, max = 1000, message = "Câu hỏi không được vượt quá 1000 ký tự")
	private String question;

	private String answer;
}
