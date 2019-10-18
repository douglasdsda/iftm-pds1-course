package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educaweb.course.dto.PaymentDTO;
import com.educaweb.course.entities.Payment;
import com.educaweb.course.repositories.PaymentRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	public List<PaymentDTO> findAll() {
		List<Payment> list = repository.findAll();
		return list.stream().map(e -> new PaymentDTO(e)).collect(Collectors.toList());
	}
	
	public PaymentDTO findById(Long id) {
		Payment entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new PaymentDTO(entity);

	}

}
