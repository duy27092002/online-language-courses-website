package com.javaproject.admin.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyPolicyTermsOfServiceDTO extends BaseDTO<PrivacyPolicyTermsOfServiceDTO> {
	@NotBlank(message = "Vui lòng không bỏ trống!")
	private String privacyPolicy;
	
	@NotBlank(message = "Vui lòng không bỏ trống!")
	private String termsOfService;
}
