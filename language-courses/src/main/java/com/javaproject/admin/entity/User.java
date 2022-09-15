package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	private String name;

	@Column(length = 500)
	private String avatar;

	@Column
	private Date dob;

	@Column
	private byte gender;

	@Column(length = 20)
	private String email;

	@Column(length = 20)
	private String phoneNumber;

	@Column(length = 50)
	private String userName;

	@Column(length = 180)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void addRole(Role role) {
		this.setRole(role);
	}
	
}
