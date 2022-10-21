package com.javaproject.admin.dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangePasswordDTO {
	String oldPass;
	
	@Pattern(regexp = "^.{6,}$", message = "Mật khẩu phải có ít nhất 6 ký tự")
	String newPass;
	
	String reNewPass;
	
	String token;
	
	String email;
}
