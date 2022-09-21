package com.javaproject.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment extends BaseEntity {
	@Column(length = 2000)
	private String content;

	@Column
	private Date time;

	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false)
	private Video video;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "feedback_for_user_id", nullable = true)
	private User feedbackFor;
}
