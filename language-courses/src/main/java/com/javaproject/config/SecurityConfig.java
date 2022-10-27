package com.javaproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.javaproject.security.CustomSuccessHandler;

import lombok.RequiredArgsConstructor;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/", "/trang-chu", "/ve-chung-toi", "/danh-sach-khoa-hoc", "/chi-tiet-khoa-hoc",
						"/danh-sach-giang-vien", "/danh-gia-cua-hoc-vien", "/lien-he", "/dang-nhap", "/dang-ky",
						"/dang-ki", "/web/**", "/admin/**", "/image-file/**", "/gui-phan-hoi", "/cau-hoi-thuong-gap")
				.permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		http.exceptionHandling().accessDeniedPage("/WEB-INF/views/common/403.jsp");

		http.authorizeRequests().and().formLogin().loginPage("/dang-nhap").loginProcessingUrl("/check_login")
				.successHandler(successHandler()).failureUrl("/dang-nhap?loi").usernameParameter("email")
				.passwordParameter("password").and().logout().logoutUrl("/dang-xuat").logoutSuccessUrl("/dang-nhap");
	}

	@Bean
	CustomSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
