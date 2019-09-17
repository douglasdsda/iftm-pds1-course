package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educaweb.course.dto.UserDTO;
import com.educaweb.course.entities.User;
import com.educaweb.course.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> dtos = service.findAll();
		// return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findId(@PathVariable Long id) {
		UserDTO dto = service.findById(id);
		// return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping()
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	

}
