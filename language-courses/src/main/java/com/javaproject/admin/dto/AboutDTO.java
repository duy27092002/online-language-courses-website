package com.javaproject.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutDTO extends BaseDTO<AboutDTO> {
	@NotBlank(message = "Vui lòng nhập tên website!")
	@NotNull(message = "Không được để trống tên website!")
	@Length(min = 1, max = 10, message = "Độ dài tên không được quá 10 ký tự")
	private String name;

	@NotBlank(message = "Vui lòng nhập địa chỉ!")
	@NotNull(message = "Không được để trống địa chỉ!")
	@Length(min = 1, max = 50, message = "Số lượng ký tự đã vượt quá ngưỡng cho phép là 50 ký tự")
	private String location;

	@NotNull(message = "Không được để trống email!")
	@Length(min = 1, max = 50, message = "Email không được vượt quá 50 ký tự")
	@Email(message = "Sai định dạng email!")
	@NotBlank(message = "Vui lòng nhập email!")
	private String email;

	@NotNull(message = "Không được để trống số điện thoại!")
	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Sai định dạng số điện thoại cá nhân!")
	@NotBlank(message = "Vui lòng nhập số điện thoại!")
	private String phoneNumber;

	@NotNull(message = "Không được để trống trường này!")
	private String twitterLink;

	@NotNull(message = "Không được để trống trường này!")
	private String facebookLink;

	@NotNull(message = "Không được để trống trường này!")
	private String instagramLink;

	@NotNull(message = "Không được để trống trường này!")
	private String youtubeLink;

	@NotNull(message = "Không được để trống trường này!")
	private String inLink;
}
