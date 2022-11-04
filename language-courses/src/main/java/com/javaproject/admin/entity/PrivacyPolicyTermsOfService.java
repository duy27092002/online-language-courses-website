package com.javaproject.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "privacy_policy_terms_of_service")
@Getter
@Setter
public class PrivacyPolicyTermsOfService extends BaseEntity {
	@Column(columnDefinition = "TEXT", nullable = false)
	private String privacyPolicy;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String termsOfService;
}
