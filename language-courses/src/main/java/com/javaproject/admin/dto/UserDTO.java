package com.javaproject.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject.admin.customannotaion.CheckDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO<UserDTO> {
	@Length(min = 1, max = 50, message = "Vui lòng nhập tên (không vượt quá 50 ký tự)")
	private String name;

	private String avatar;
	MultipartFile fileImage;

//	@Past(message = "Ngày sinh không hợp lệ")
//	private Date dob;
	@CheckDate(message = "Ngày sinh không hợp lệ")
	private String dob;

	private byte gender;

	@Length(min = 1, max = 50, message = "Vui lòng nhập email (không vượt quá 50 ký tự")
	@Email(message = "Sai định dạng email")
	private String email;

	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Số điện thoại không hợp lệ")
	private String phoneNumber;

	@Length(min = 1, max = 50, message = "Tên tài khoản không được để trống và không vượt quá 50 ký tự")
	private String userName;

	@Pattern(regexp = "^\\b.{6,15}\\b$", message = "Mật khẩu phải có từ 6 đến 15 ký tự và không chứa khoảng trắng ở đầu và cuối")
	private String password;
	
	@NotNull(message = "Vui lòng chọn vai trò")
	private Long roleId;

	private String twitterLink;

	private String facebookLink;

	private String instagramLink;

	private String youtubeLink;

	private String inLink;
	
	// response
	private String roleName;
}
