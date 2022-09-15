package com.javaproject.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
	@Column(length = 20)
	private String name;
	
	public Role(String name) {
		this.name = name;
	}

	@OneToOne(mappedBy = "role")
	private User user;

	@Override
	public String toString() {
		return this.name;
	}
}
