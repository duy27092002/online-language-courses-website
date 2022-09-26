package com.javaproject.admin.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
	@Column(length = 20, nullable = false, unique = true)
	private String name;

	@Column(length = 20, nullable = false)
	private String code;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();
}
