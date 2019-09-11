package com.educaweb.course.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.entities.Category;
import com.educaweb.course.repositories.CategoryRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id){
		return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	public Category insert(Category obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Category update(Long id, Category obj) {
		try {
		Category entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
		} catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
