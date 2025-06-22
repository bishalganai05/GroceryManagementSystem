package com.bishal.gms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "ProductTable")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;
	@Column
	private String productName;
	@Column
	private String productType;
	@Column
	private float productPrice;
	
	public Product() {}
	
	public Product(String productName, String productType, float productPrice) {
		this.productName = productName;
		this.productType = productType;
		this.productPrice = productPrice;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Grocery [productID=" + productID + ", productName=" + productName + ", productType=" + productType
				+ ", productPrice=" + productPrice + "]";
	}
	
	
}
