package com.javaproject.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "evaluated")
@Getter
@Setter
public class Evaluated extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(length = 1000, nullable = false)
	private String content;

	@Column(nullable = false)
	private int point;
}
