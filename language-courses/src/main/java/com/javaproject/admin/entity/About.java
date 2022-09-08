package com.javaproject.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "about")
@Getter
@Setter
public class About extends BaseEntity {
	@Column(length = 10)
	private String name;

	@Column(length = 50)
	private String location;

	@Column(length = 20)
	private String email;

	@Column(length = 20)
	private String phoneNumber;

	@Column(name = "twitter_link")
	private String twitterLink;

	@Column(name = "facebook_link")
	private String facebookLink;

	@Column(name = "instagram_link")
	private String instagramLink;

	@Column(name = "youtube_link")
	private String youtubeLink;

	@Column(name = "in_link")
	private String inLink;
}
