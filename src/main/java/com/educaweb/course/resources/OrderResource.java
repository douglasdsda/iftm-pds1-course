package com.educaweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaweb.course.dto.OrderDTO;
import com.educaweb.course.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> users = service.findAll();
		// return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> findId(@PathVariable Long id) {
		OrderDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

}
