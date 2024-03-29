package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educaweb.course.dto.CategoryDTO;
import com.educaweb.course.services.CategoryService;

@RestController
@RequestMapping("/categories" )
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	

	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> dtos = service.findAll();
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<List<CategoryDTO>> findByProduct(@PathVariable Long productId){
		List<CategoryDTO> dtos = service.findByProduct(productId);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dtos);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findId(@PathVariable Long id){
		CategoryDTO dto = service.findById(id);
		//return ResponseEntity.ok().body(u);
		return ResponseEntity.ok().body(dto);
	}
	

	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto) {
		CategoryDTO  newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();

		return ResponseEntity.created(uri).body(newDto);

	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	

}
