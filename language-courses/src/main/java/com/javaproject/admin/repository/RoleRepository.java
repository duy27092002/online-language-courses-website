package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Role;

public interface RoleRepository extends SearchingRepository<Role, Long> {
	@Query("select r from #{#entityName} r where r.name like %?1%")
	Page<Role> getSearchList(String keyword, Pageable pageable);
	
	@Query("select r from #{#entityName} r")
	Page<Role> getAllList(Pageable pageable);
	
	Role findByName(String name);
	
	List<Role> findByStatus(int status);
}
