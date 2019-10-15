package com.educaweb.course.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.educaweb.course.entities.Category;
import com.educaweb.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Product obj"
			+ " inner join obj.categories cats"
			+ "	where :category in cats")
	Page<Product> findByCategory(Category category, Pageable pageable);

}
