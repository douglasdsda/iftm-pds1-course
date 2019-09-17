package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.dto.UserDTO;
import com.educaweb.course.entities.User;
import com.educaweb.course.repositories.UserRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> list =  repository.findAll();
		return list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
		
	}
	
	public UserDTO findById(Long id){
		User entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
		
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		try {
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
		} catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
