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
	@Column(length = 10, nullable = false)
	private String name;

	@Column(length = 50, nullable = false)
	private String location;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 20, nullable = false)
	private String phoneNumber;

	@Column(name = "twitter_link", nullable = false)
	private String twitterLink;

	@Column(name = "facebook_link", nullable = false)
	private String facebookLink;

	@Column(name = "instagram_link", nullable = false)
	private String instagramLink;

	@Column(name = "youtube_link", nullable = false)
	private String youtubeLink;

	@Column(name = "in_link", nullable = false)
	private String inLink;
	
	@Column(name = "favicon")
	private String favicon;
	
	@Column(name = "logo")
	private String logo;
}
