package com.educaweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaweb.course.entities.User;

@RestController
@RequestMapping("/users" )
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "joao", "joao@teste", "42342-342", "123");
		return ResponseEntity.ok().body(u);
	}

}
