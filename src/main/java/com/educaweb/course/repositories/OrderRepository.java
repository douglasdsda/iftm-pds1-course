package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
