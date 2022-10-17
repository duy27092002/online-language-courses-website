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
//@EnableGlobalMethodSecurity(prePostEnabled = false, securedEnabled = false, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;

	//private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);//.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		CustomAuthenticationFilter customAuthenticaionFilter = new CustomAuthenticationFilter(
//				authenticationManagerBean());
//		customAuthenticaionFilter.setFilterProcessesUrl("/api/auth/login");

		http.csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
				.antMatchers("/", "/trang-chu", "/ve-chung-toi", "/danh-sach-khoa-hoc", "/chi-tiet-khoa-hoc",
						"/danh-sach-giang-vien", "/danh-gia-cua-hoc-vien", "/lien-he", "/dang-nhap", "/dang-ky",
						"/dang-ki", "/web/**", "/admin/**", "/403")
				.permitAll();

		http.authorizeRequests().anyRequest().authenticated();

		http.authorizeRequests().and().formLogin().loginPage("/dang-nhap")
				.loginProcessingUrl("/check_login").successHandler(successHandler())
				.failureUrl("/dang-nhap?loi").usernameParameter("email").passwordParameter("password").and().logout()
				.logoutUrl("/dang-xuat").logoutSuccessUrl("/dang-nhap");

//		http.addFilter(customAuthenticaionFilter);
//		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
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
