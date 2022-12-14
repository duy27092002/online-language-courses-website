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
	@Column(nullable = false)
	private String name;

	@Column(length = 1000, nullable = false)
	private String thumbnail;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "course_skill", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "skill_level_id"))
	private List<SkillLevel> skillLevelList = new ArrayList<>();

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(nullable = false, length = 20)
	private String price;

	@Column(nullable = true)
	private int discount;

	@Column(nullable = true)
	private Date startDiscountTime;

	@Column(nullable = true)
	private Date endDiscountTime;

	@Column(nullable = true)
	private Date releaseTime;

	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<CourseStudent> studentList = new ArrayList<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "course_instructor", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> instructors = new ArrayList<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Video> videos = new ArrayList<>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Evaluated> evaluatedList = new ArrayList<>();

	public void addSkillLevel(SkillLevel skl) {
		this.skillLevelList.add(skl);
		skl.getCourses().add(this);
	}
	
	public void addInstructor(User instructor) {
		this.instructors.add(instructor);
		instructor.getCourses().add(this);
	}
}
