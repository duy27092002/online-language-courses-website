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
public class RoleDTO extends BaseDTO<RoleDTO> {
	@NotBlank(message = "Không được để trống tên vai trò")
	@Length(min = 1, max = 20, message = "Tên không được vượt quá 20 ký tự")
	private String name;

	private String code;
}
