package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "video")
@Getter
@Setter
public class Video extends BaseEntity {
	@Column(length = 500, nullable = false)
	private String videoFile;

	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String thumbnail;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
}
