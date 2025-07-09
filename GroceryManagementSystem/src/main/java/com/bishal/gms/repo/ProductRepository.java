package com.bishal.gms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bishal.gms.entity.Product;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAll();
	List<Product> findByProductTypeOrderByProductPrice(String productType);
	
	@Query("select product from Product product where product.productName like %:name%")
	List<Product> findProductByPartialProductName(@Param("name") String name);
}
