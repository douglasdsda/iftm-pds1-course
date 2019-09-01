package com.educaweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaweb.course.entities.Product;
import com.educaweb.course.services.ProductService;

@RestController
@RequestMapping("/products" )
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> users = service.findAll();
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findId(@PathVariable Long id){
		Product user = service.findById(id);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(user);
	}

}
