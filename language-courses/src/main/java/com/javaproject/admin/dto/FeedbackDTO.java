package com.javaproject.admin.dto;

import javax.validation.constraints.Email;
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
public class FeedbackDTO extends BaseDTO<FeedbackDTO> {
	@NotNull(message = "Không được để trống tên của bạn!")
	@NotBlank(message = "Vui lòng nhập tên của bạn!")
	@Length(min = 1, max = 50, message = "Tên không được vượt quá 50 ký tự")
	private String name;

	@NotNull(message = "Không được để trống email!")
	@Length(min = 1, max = 50, message = "Email không được vượt quá 50 ký tự")
	@Email(message = "Sai định dạng email!")
	@NotBlank(message = "Vui lòng nhập email!")
	private String email;
	
	@NotNull(message = "Không được để trống câu hỏi!")
	@NotBlank(message = "Vui lòng nhập câu hỏi của bạn!")
	@Length(min = 1, max = 1000, message = "Câu hỏi không được vượt quá 1000 ký tự")
	private String question;
}
