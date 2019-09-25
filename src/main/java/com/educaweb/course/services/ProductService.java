package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educaweb.course.dto.CategoryDTO;
import com.educaweb.course.dto.ProductCategoriesDTO;
import com.educaweb.course.dto.ProductDTO;
import com.educaweb.course.entities.Category;
import com.educaweb.course.entities.Product;
import com.educaweb.course.repositories.CategoryRepository;
import com.educaweb.course.repositories.ProductRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository repositoryCategories;
	
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id){
		Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO insert(ProductCategoriesDTO dto) {
		Product entity = dto.toEntity();
		setProductCategories(entity, dto.getCategories());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	
	private void setProductCategories(Product entity, List<CategoryDTO> categories) {
		entity.getCategories().clear();
		
		for(CategoryDTO dto: categories) {
			Category category = repositoryCategories.getOne(dto.getId());
			entity.getCategories().add(category);
		}
	}
	
}
