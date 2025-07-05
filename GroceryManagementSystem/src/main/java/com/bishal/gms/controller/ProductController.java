package com.bishal.gms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.gms.entity.Product;
import com.bishal.gms.exception.IDNotFoundException;
import com.bishal.gms.exception.MandatoryFieldException;
import com.bishal.gms.service.CacheInspectionService;
import com.bishal.gms.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;
	private final CacheInspectionService cacheInspectionService;

	public ProductController(ProductService productService, CacheInspectionService cacheInspectionService) {
		this.productService = productService;
		this.cacheInspectionService = cacheInspectionService;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('PRODUCT_READ')")	
	public List<Product> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return products;
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('PRODUCT_READ')")	
	public Product getProductById(@PathVariable int id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			throw new IDNotFoundException("Product not found for the id ");
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('PRODUCT_WRITE')")	
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		if(product.getProductName().isEmpty()) {
			throw new MandatoryFieldException("Mandatory field missing");
		}
		Product newProduct = productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('PRODUCT_WRITE')")
	public void updateProduct(@RequestBody Product product1,@PathVariable int id) {
		Product product = productService.getProductById(id).get();
		product.setProductName(product1.getProductName());
		product.setProductType(product1.getProductType());
		product.setProductPrice(product1.getProductPrice());
		productService.addProduct(product);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('PRODUCT_DELETE')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/health")
	@PreAuthorize("hasAuthority('PRODUCT_READ')")	
    public String getHealth() {
        return "Healthy";
    }
	
	@GetMapping("/csrf")
	@PreAuthorize("hasAuthority('PRODUCT_READ')")	
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	@GetMapping("/cacheData")
	public void getCache() {
		cacheInspectionService.printCacheContents("products");
	}
	
}
