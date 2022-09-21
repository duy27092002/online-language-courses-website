package com.javaproject.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Setter
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("DA DI VAO PHAN DIEU HUONG");
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication auth) {
		String url = "";

		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		String getRoleName = "";
		for (GrantedAuthority roleName : roles) {
			getRoleName = roleName.getAuthority();
		}
		
		log.info("LAY ROLE CHUAN BI DIEU HUONG: {}", getRoleName);

		if (isAdminOrInstructor(getRoleName)) {
			log.info("DAY LA TRANG ADMIN");
			url = "/quan-tri";
		} else if (isStudent(getRoleName)) {
			log.info("DAY LA TRANG WEB");
			url = "/trang-chu";
		}
		return url;
	}

	private boolean isAdminOrInstructor(String roleName) {
		if (roleName.contains("ADMIN") || roleName.contains("INSTRUCTOR")) {
			return true;
		}

		return false;
	}

	private boolean isStudent(String roleName) {
		return roleName.contains("giang-vien");
	}
}
