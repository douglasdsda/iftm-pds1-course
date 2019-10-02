package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.dto.CategoryDTO;
import com.educaweb.course.entities.Category;
import com.educaweb.course.repositories.CategoryRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(e -> new CategoryDTO(e)).collect(Collectors.toList());
	}
	
	public CategoryDTO findById(Long id) {
		Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new CategoryDTO(entity);

	}
	
	
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = dto.toEntity();
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
	
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = repository.getOne(id);
		updateData(entity, dto);
		entity =  repository.save(entity);
		return new CategoryDTO(entity);
		} catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, CategoryDTO obj) {
		entity.setName(obj.getName());
	}
}
