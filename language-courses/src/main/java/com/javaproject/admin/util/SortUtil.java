package com.javaproject.admin.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortUtil {
	public Sort handleSord(String orderBy, String orderType) {
		Sort sort = null;
		if (orderType.toLowerCase().equalsIgnoreCase("asc")) {
			sort = Sort.by(orderBy.toLowerCase()).ascending();
		} else if (orderType.toLowerCase().equalsIgnoreCase("desc")) {
			sort = Sort.by(orderBy.toLowerCase()).descending();
		}
		return sort;
	}
}
