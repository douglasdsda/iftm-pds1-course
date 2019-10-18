package com.educaweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educaweb.course.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
