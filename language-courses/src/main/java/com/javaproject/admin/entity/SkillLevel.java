package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skill_level")
@Getter
@Setter
public class SkillLevel extends BaseEntity {
	@Column(length = 20, nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "skillLevelList", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Course> courses = new ArrayList<>();
}
