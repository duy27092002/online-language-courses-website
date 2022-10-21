package com.javaproject.util;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javaproject.admin.dto.AccountDetails;

public class SecurityUtil {
	public static AccountDetails getPrincipal() {
		AccountDetails accDetails = (AccountDetails) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return accDetails;
    }
	
	@SuppressWarnings("unchecked")
	public static String getAuthorities() {
		String getRole = null;
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            getRole = authority.getAuthority();
        }
		return getRole;
	}
}
