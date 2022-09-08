package com.javaproject.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.admin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
