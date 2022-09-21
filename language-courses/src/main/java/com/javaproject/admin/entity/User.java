package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 500)
	private String avatar;

	@Column(nullable = false)
	private Date dob;

	@Column(nullable = false)
	private byte gender;

	@Column(length = 20, nullable = false)
	private String email;

	@Column(length = 20, nullable = false)
	private String phoneNumber;

	@Column(length = 50, nullable = false)
	private String userName;

	@Column(length = 180, nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(name = "twitter_link", nullable = true)
	private String twitterLink;

	@Column(name = "facebook_link", nullable = true)
	private String facebookLink;

	@Column(name = "instagram_link", nullable = true)
	private String instagramLink;

	@Column(name = "youtube_link", nullable = true)
	private String youtubeLink;

	@Column(name = "in_link", nullable = true)
	private String inLink;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CourseUser> courseUserList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Video> videos = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Evaluated> evaluatedList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "feedbackFor", cascade = CascadeType.ALL)
	private List<Comment> feedbackCommentsList = new ArrayList<>();
}
