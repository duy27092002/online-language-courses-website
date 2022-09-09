package com.javaproject.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaproject.admin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("select r from #{#entityName} r where r.name like %?1%")
	List<Role> getSearchListByName(String keyword, Pageable pageable);
}
