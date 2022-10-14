package com.javaproject.admin.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.javaproject.admin.customannotaion.CheckDate;

public class DateValidator implements ConstraintValidator<CheckDate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.trim().length() == 0) {
			return false;
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = LocalDateTime.now().toString();
		try {
			Date getCurrentDate = df.parse(currentDate);
			Date dateFmt = df.parse(value);
			if (getCurrentDate.compareTo(dateFmt) < 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
