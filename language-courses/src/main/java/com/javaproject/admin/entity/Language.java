package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language")
@Getter
@Setter
public class Language extends BaseEntity {
	@Column(length = 20, nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<>();
}
