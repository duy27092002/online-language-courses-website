package com.javaproject.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaproject.admin.entity.User;
import com.javaproject.admin.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy " + email));
		
		if (user != null) {
			log.info("Da tim thay user: {}", email);
		} else {
			log.error("Khong tim thay user: {}", email);
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().getCode()));
		
		log.info("Password: {}", user.getPassword());
		log.info("Role: {}", authorities.toString());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                authorities);
	}

}
