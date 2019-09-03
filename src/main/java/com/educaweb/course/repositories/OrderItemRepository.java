package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
