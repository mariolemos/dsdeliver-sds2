package com.devsuperior.dsdeliver.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entites.Order;
import com.devsuperior.dsdeliver.entites.OrderStatus;
import com.devsuperior.dsdeliver.entites.Product;
import com.devsuperior.dsdeliver.repository.OrderRepository;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts(); 
			return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());		
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	
	
}
