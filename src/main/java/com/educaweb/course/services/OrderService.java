package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.dto.OrderDTO;
import com.educaweb.course.entities.Order;
import com.educaweb.course.repositories.OrderRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findAll();
		return list.stream().map(e -> new OrderDTO(e)).collect(Collectors.toList());
	}

	public OrderDTO findById(Long id) {
		Order entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new OrderDTO(entity);

	}
}
