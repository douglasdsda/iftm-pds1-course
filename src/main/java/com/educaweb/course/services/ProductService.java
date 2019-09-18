package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.dto.ProductDTO;
import com.educaweb.course.entities.Product;
import com.educaweb.course.repositories.ProductRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id){
		Product entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
	
}
