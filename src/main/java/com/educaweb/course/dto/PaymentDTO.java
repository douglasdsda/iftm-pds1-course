package com.educaweb.course.dto;

import java.io.Serializable;
import java.time.Instant;

import com.educaweb.course.entities.Order;
import com.educaweb.course.entities.Payment;

public class PaymentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
		private Long id;
		private Instant moment;
		private Long orderId;
		
		public PaymentDTO() {}

		public PaymentDTO(Long id, Instant moment, Long orderId) {
			super();
			this.id = id;
			this.moment = moment;
			this.orderId = orderId;
		}
		
		public PaymentDTO(Payment entity) {
			this.id = entity.getId();
			this.moment = entity.getMoment();
			this.orderId = entity.getOrder().getId();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Instant getMoment() {
			return moment;
		}

		public void setMoment(Instant momment) {
			this.moment = momment;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		
		public Payment toEntity(PaymentDTO dto) {
			Order order = new Order(orderId, null, null, null);
			return new Payment(id, moment, order);
		}
		
		
}
