package com.educaweb.course.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.educaweb.course.entities.Order;
import com.educaweb.course.entities.User;
import com.educaweb.course.entities.enums.OrdersStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	private OrdersStatus ordersStatus;
	private Long clientId;
	private String clientName;
	private String clientEmail;

	public OrderDTO() {
	}

	public OrderDTO(Long id, Instant moment, OrdersStatus ordersStatus, Long clientId, String clientName,
			String clientEmail) {
		this.id = id;
		this.moment = moment;
		this.ordersStatus = ordersStatus;
		this.clientId = clientId;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
	}

	public OrderDTO(Order entity) {
		if (entity.getClient() == null) {
			throw new IllegalArgumentException("Error Instantiating OrderDTO: client was null");
		}
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.ordersStatus = entity.getOrdersStatus();
		this.clientId = entity.getClient().getId();
		this.clientName = entity.getClient().getName();
		this.clientEmail = entity.getClient().getEmail();
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

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrdersStatus getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(OrdersStatus ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public Order toEntity() {
		User client = new User(clientId, clientName, clientEmail, null, null);
		return new Order(id, moment, ordersStatus, client);
	}

}
