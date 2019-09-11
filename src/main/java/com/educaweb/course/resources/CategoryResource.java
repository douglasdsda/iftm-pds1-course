package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping()
	public ResponseEntity<Category> insert(@RequestBody Category obj) {
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
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	

}
