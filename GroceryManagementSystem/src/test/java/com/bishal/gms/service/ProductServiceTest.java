package com.bishal.gms.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bishal.gms.entity.Product;
import com.bishal.gms.repo.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	ProductRepository productRepository;
	@InjectMocks
	ProductService productService;
	

	@Test
	void addProductToDatabase() {
		Product product = new Product();
		product.setProductID(19);
		product.setProductName("Coke");
		product.setProductType("Cold-Drinks");
		product.setProductPrice(24);
		
		Mockito.when(productRepository.save(product)).thenReturn(product);
		Product addedProduct = productService.addProduct(product);
		
		Assertions.assertEquals(product.getProductID(), addedProduct.getProductID());
		
		System.out.println("Added Product: " + addedProduct);
	}
}
