package com.educaweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaweb.course.entities.Category;
import com.educaweb.course.services.CategoryService;

@RestController
@RequestMapping("/categories" )
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> users = service.findAll();
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findId(@PathVariable Long id){
		Category user = service.findById(id);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(user);
	}

}
