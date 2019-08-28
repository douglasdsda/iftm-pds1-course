package com.educaweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educaweb.course.entities.User;
import com.educaweb.course.services.UserService;

@RestController
@RequestMapping("/users" )
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = service.findAll();
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findId(@PathVariable Long id){
		User user = service.findById(id);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(user);
	}

}
