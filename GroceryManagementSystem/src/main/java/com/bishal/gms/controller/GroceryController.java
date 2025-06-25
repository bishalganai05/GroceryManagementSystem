package com.bishal.gms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.gms.entity.Product;
import com.bishal.gms.exception.IDNotFoundException;
import com.bishal.gms.exception.MandatoryFieldException;
import com.bishal.gms.repo.ProductRepository;

@RestController
public class GroceryController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products;
	}
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable int id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			throw new IDNotFoundException("Product not found for the id ");
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if(product.getProductName().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		Product newProduct = productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@PutMapping("/products/{id}")
	public void updateProduct(@RequestBody Product product1,@PathVariable int id) {
		Product product = productRepository.findById(id).get();
		product.setProductName(product1.getProductName());
		product.setProductType(product1.getProductType());
		product.setProductPrice(product1.getProductPrice());
		productRepository.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
	}
	
	@GetMapping("/health")
    public String getHealth() {
        return "Healthy";
    }
}
