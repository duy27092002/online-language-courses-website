package com.javaproject.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "faqs")
@Getter
@Setter
public class FAQs extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 20, nullable = false)
	private String email;

	@Column(length = 1000, nullable = false)
	private String question;

	@Column(columnDefinition = "TEXT", nullable = true)
	private String answer;
}
