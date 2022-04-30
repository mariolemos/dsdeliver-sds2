package com.devsuperior.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entites.Order;
import com.devsuperior.dsdeliver.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts(); 
			return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());		
	}	
	
}
