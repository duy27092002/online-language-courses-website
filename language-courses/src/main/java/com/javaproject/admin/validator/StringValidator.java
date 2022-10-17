package com.javaproject.admin.validator;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.javaproject.admin.customannotaion.CheckString;
import com.javaproject.admin.dto.RoleDTO;

public class StringValidator implements ConstraintValidator<CheckString, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		int strLength = value.trim().length();
		try {
			RoleDTO roleDTO = new RoleDTO();
			Field field = roleDTO.getClass().getDeclaredField("name");
			CheckString checkStr = field.getAnnotation(CheckString.class);
			int minLength = checkStr.minLength();
			int maxLength = checkStr.maxLength();
			if (strLength < minLength || strLength > maxLength) {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Lỗi ở isValid method");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
