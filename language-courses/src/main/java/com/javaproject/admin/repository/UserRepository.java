package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.User;

public interface UserRepository extends SearchingRepository<User, Long> {
	User findByEmail(String email);
	
	User findByEmailAndStatus(String email, int status);
	
	User findByPhoneNumber(String phoneNumber);
	
	@Query("select u from #{#entityName} u where u.name like %?1% or u.email like %?1%")
	Page<User> getSearchList(String keyword, Pageable pageable);
	
	@Query("select u from #{#entityName} u")
	Page<User> getAllList(Pageable pageable);
	
	List<User> findByRoleIdAndStatus(long roleId, int statys);
}
