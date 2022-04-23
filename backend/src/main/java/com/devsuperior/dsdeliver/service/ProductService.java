package com.devsuperior.dsdeliver.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entites.Product;
import com.devsuperior.dsdeliver.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc(); 
			return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());		
	}
	

}