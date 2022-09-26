package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
	User findByPhoneNumber(String phoneNumber);
	
	@Query("select u from #{#entityName} u where u.name like %?1% or u.email like %?1%")
	List<User> getSearchList(String keyword, Pageable pageable);
}
