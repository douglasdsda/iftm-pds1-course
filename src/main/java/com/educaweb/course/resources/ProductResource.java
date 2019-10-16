package com.educaweb.course.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educaweb.course.dto.CategoryDTO;
import com.educaweb.course.dto.ProductCategoriesDTO;
import com.educaweb.course.dto.ProductDTO;
import com.educaweb.course.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProductDTO> list = service.findByNameCategoryPaged(name , categories, pageRequest);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/category/{categoryId}")
	public ResponseEntity<Page<ProductDTO>> findByCategoryPaged(
			@PathVariable Long categoryId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProductDTO> list = service.findByCategoryPaged(categoryId,pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> findId(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductCategoriesDTO dto) {
		ProductDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();

		return ResponseEntity.created(uri).body(newDto);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductCategoriesDTO dto) {
		ProductDTO pdto = service.update(id, dto);
		return ResponseEntity.ok().body(pdto);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/addcategory")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> addCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
		service.addCategory(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/removecategory")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> removeCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO dto) {
		service.removeCategory(id, dto);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/setcategories")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> setCategory(@PathVariable Long id, @Valid @RequestBody List<CategoryDTO> dto) {
		service.setCategories(id, dto);
		return ResponseEntity.noContent().build();
	}

}
