package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
