package com.javaproject.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javaproject.admin.dto.AccountDetails;
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
		User user = userRepo.findByEmailAndStatus(email, 1);

		if (user != null) {
			log.info("Da tim thay user: {}", email);
		} else {
			log.error("Khong tim thay user: {}", email);
			throw new UsernameNotFoundException("Không tìm thấy " + email);
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getRole().getCode()));

		log.info("Password: {}", user.getPassword());
		log.info("Role: {}", authorities.toString());

		AccountDetails accDetails = new AccountDetails(user.getEmail(), user.getPassword(), true, true, true, true,
				authorities);
		accDetails.setUserId(user.getId());
		accDetails.setAvatar(user.getAvatar());

		return accDetails;
	}

}
