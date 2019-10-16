package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educaweb.course.dto.CategoryDTO;
import com.educaweb.course.dto.OrderDTO;
import com.educaweb.course.dto.OrderItemDTO;
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
	
	@GetMapping("/{id}/items")
	public ResponseEntity<List<OrderItemDTO>> findItems(@PathVariable Long id) {
		List<OrderItemDTO> list = service.findItems(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/myorders")
	public ResponseEntity<List<OrderDTO>> findByClient() {
		List<OrderDTO> list = service.findByClient();
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<OrderDTO>> findByClientId(@PathVariable Long clientId) {
		List<OrderDTO> list = service.findByClientId(clientId);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody List<OrderItemDTO> dto) {
		OrderDTO  orderDTO = service.placeOrder(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(orderDTO);
	}

}
