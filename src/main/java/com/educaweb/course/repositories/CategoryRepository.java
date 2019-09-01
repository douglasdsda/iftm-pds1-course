package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
