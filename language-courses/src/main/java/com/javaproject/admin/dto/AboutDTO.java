package com.javaproject.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
public class AboutDTO extends BaseDTO<AboutDTO> {
	@Length(min = 1, max = 10, message = "Vui lòng nhập tên website với độ dài không quá 10 ký tự")
	private String name;

	@Length(min = 1, max = 50, message = "Vui lòng nhập địa chỉ với độ dài không 50 ký tự")
	private String location;

	@Length(min = 1, max = 50, message = "Vui lòng nhập email với độ dài không vượt quá 50 ký tự")
	@Email(message = "Sai định dạng email!")
	private String email;

	@Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Số điện thoại không hợp lệ")
	private String phoneNumber;

	@NotBlank(message = "Không được để trống trường này!")
	private String twitterLink;

	@NotBlank(message = "Không được để trống trường này!")
	private String facebookLink;

	@NotBlank(message = "Không được để trống trường này!")
	private String instagramLink;

	@NotBlank(message = "Không được để trống trường này!")
	private String youtubeLink;

	@NotBlank(message = "Không được để trống trường này!")
	private String inLink;
	
	private String favicon;
	private MultipartFile faviconFile;
	
	private String logo;
	private MultipartFile logoFile;
}
