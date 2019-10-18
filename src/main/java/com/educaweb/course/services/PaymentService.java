package com.educaweb.course.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.educaweb.course.dto.PaymentDTO;
import com.educaweb.course.entities.Order;
import com.educaweb.course.entities.Payment;
import com.educaweb.course.repositories.OrderRepository;
import com.educaweb.course.repositories.PaymentRepository;
import com.educaweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private OrderRepository OrderRepository;
	
	public List<PaymentDTO> findAll() {
		List<Payment> list = repository.findAll();
		return list.stream().map(e -> new PaymentDTO(e)).collect(Collectors.toList());
	}
	
	public PaymentDTO findById(Long id) {
		Payment entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new PaymentDTO(entity);

	}
	
	
	@Transactional
	public PaymentDTO insert(PaymentDTO dto) {
		Order order = OrderRepository.getOne(dto.getOrderId());
		
		Payment payment = new Payment(null, dto.getMoment(), order);
		order.setPayment(payment);
		
		OrderRepository.save(order);
		
		 return new PaymentDTO(order.getPayment());
	}
	
	@Transactional
	public PaymentDTO update(Long id, PaymentDTO dto) {
		try {
		Payment entity = repository.getOne(id);
		updateData(entity, dto);
		entity =  repository.save(entity);
		return new PaymentDTO(entity);
		} catch(EntityNotFoundException e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Payment entity, PaymentDTO obj) {
		entity.setMoment(obj.getMoment());
	}

}
