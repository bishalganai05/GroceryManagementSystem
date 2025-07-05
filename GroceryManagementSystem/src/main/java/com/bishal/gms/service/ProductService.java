package com.bishal.gms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	@CachePut(value = "products", key = "#result.productID") 
	public Product addProduct(Product product) {
	    return productRepository.save(product);
	}

	@Cacheable(value = "products", key = "#id") 
	public Optional<Product> getProductById(int id) {
	    return productRepository.findById(id);
	}

	@CacheEvict(value = "products", key = "#id") 
	public void deleteProduct(int id) {
	    productRepository.deleteById(id);
	}
	
	@SuppressWarnings("unused")
	private Boolean testMethodForPrivateCase(String name) {
		name = "Bishal";
		System.out.println("Private test case executed "+name);
		return true;
	}
}
