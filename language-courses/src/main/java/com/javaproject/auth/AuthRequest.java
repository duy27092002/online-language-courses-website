package com.javaproject.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
	@NotNull(message = "Không được để trống email!")
	@Length(min = 1, max = 20, message = "Email không được vượt quá 20 ký tự")
	@Email(message = "Sai định dạng email!")
	@NotBlank(message = "Vui lòng nhập email!")
	private String email;
	
	@NotNull(message = "Không được để trống mật khẩu!")
	@NotBlank(message = "Vui lòng nhập mật khẩu!")
	private String password;
}
