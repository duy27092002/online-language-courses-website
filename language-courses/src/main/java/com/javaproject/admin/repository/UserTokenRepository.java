package com.javaproject.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.admin.entity.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
	UserToken findFirstByEmailAndType(String email, Integer type);
}
