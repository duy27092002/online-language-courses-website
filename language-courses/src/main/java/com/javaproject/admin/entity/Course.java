package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
public class Course extends BaseEntity {
	@Column
	private String name;

	@Column(length = 1000)
	private String thumbnail;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "course_skill", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "skill_level_id"))
	private List<SkillLevel> skillLevelList = new ArrayList<>();

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column
	private int price;

	@Column(nullable = true)
	private int discount;

	@Column(nullable = true)
	private Date startDiscountTime;

	@Column(nullable = true)
	private Date endDiscountTime;

	@Column(nullable = true)
	private Date releaseTime;

	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseUser> courseUserList = new ArrayList<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Video> videos = new ArrayList<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Evaluated> evaluatedList = new ArrayList<>();
}
