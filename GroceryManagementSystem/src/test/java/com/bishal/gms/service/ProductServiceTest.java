package com.bishal.gms.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeAll
	public static void init() {
		System.out.println("DB connection successfull");
	}
	
	@BeforeEach
	public void runBeforeEveryTest() {
		System.out.println("######### New Test Case ######### ");
	}
	
	@AfterEach
	public void runAfterEveryTest() {
		System.out.println("######### Over ######### ");
	}
	
	@AfterAll
	public static void init_() {
		System.out.println("DB connection closed");
	}
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
	
	@Test
	void dummyTest() {
		System.out.println("Dummy Test Running");
	}
	
	@Test
	public void deleteProductFromDb() {
		doNothing().when(productRepository).deleteById(1);
		productService.deleteProduct(1);
		Mockito.verify(productRepository, times(1)).deleteById(1);
	}
	
	@Test
	void testPrivateMethod() throws Exception {
		//java reflections
		Method declaredMethod = ProductService.class.getDeclaredMethod("testMethodForPrivateCase",String.class);
		declaredMethod.setAccessible(true);
		Boolean invoke = (Boolean) declaredMethod.invoke(productService, "Amit");
		Assertions.assertTrue(invoke);
	}
}
