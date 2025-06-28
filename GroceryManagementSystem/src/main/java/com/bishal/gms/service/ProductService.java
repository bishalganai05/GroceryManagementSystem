package com.bishal.gms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bishal.gms.entity.Product;
import com.bishal.gms.repo.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(int id) {
		return productRepository.findById(id);
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	
}
