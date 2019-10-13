package com.educaweb.course.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.Order;
import com.educaweb.course.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByClient(User client);

}
