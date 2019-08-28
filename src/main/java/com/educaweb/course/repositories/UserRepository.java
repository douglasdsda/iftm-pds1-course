package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
