package com.educaweb.course.dto;

import java.io.Serializable;

import com.educaweb.course.entities.User;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String email;
	private String phone;

	public UserDTO() {

	}

	public UserDTO(Long id, String name, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public UserDTO(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
	}
	
	public User toEnity() {
		return new User(id, name,email, phone, null);
	}

}
