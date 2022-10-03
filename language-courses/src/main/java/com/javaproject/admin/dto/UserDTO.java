package com.javaproject.admin.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class UserDTO extends BaseDTO<UserDTO> {
	@NotBlank(message = "Vui lòng nhập tên!")
	@NotNull(message = "Không được để trống tên!")
	@Length(min = 1, max = 50, message = "Tên không được vượt quá 50 ký tự")
	private String name;

	private String avatar;
	MultipartFile fileImage;

	@NotNull(message = "Không được để trống ngày sinh!")
	private Date dob;

	private byte gender;

	@NotNull(message = "Không được để trống email!")
	@Length(min = 1, max = 50, message = "Email không được vượt quá 50 ký tự")
	@Email(message = "Sai định dạng email!")
	@NotBlank(message = "Vui lòng nhập email!")
	private String email;

	@NotNull(message = "Không được để trống số điện thoại!")
	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Sai định dạng số điện thoại cá nhân!")
	@NotBlank(message = "Vui lòng nhập số điện thoại!")
	private String phoneNumber;

	@NotNull(message = "Không được để trống tên đăng nhập!")
	@Length(min = 1, max = 50, message = "Tên đăng nhập không được vượt quá 50 ký tự")
	@NotBlank(message = "Vui lòng nhập tên đăng nhập!")
	private String userName;

	@NotNull(message = "Không được để trống mật khẩu!")
	@NotBlank(message = "Vui lòng nhập mật khẩu!")
	private String password;
	
	@NotNull(message = "Vui lòng chọn vai trò!")
	private Long roleId;

	private String twitterLink;

	private String facebookLink;

	private String instagramLink;

	private String youtubeLink;

	private String inLink;
	
	// response
	private String roleName;
}
