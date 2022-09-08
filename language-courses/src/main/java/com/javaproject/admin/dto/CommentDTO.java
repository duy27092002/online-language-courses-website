package com.javaproject.admin.dto;

import java.util.Date;

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
public class CommentDTO extends BaseDTO<CommentDTO> {
	@NotNull(message = "Không được để trống nội dung bình luận!")
	@NotBlank(message = "Vui lòng nhập nội dung bình luận!")
	@Length(min = 1, max = 2000, message = "Nội dung bình luận không được vượt quá 2000 ký tự")
	private String content;

	private Date time;

	private Long videoId;

	private Long userId;

	private Long feedbackForUserId;
}
