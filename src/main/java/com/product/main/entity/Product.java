package com.product.main.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
   
	@Id
	private String productId;
	
	@NotNull(message = "product name is required")
	private String productName;
	
	@Min(value = 1)
	private int productQuantity;
	
	@Min(1)
	private double productPrise;
	
	@NotNull(message = "product type is required")
	private String productType;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, String productName, int productQuantity, double productPrise, String productType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrise = productPrise;
		this.productType = productType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductPrise() {
		return productPrise;
	}

	public void setProductPrise(double productPrise) {
		this.productPrise = productPrise;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productQuantity="
				+ productQuantity + ", productPrise=" + productPrise + ", productType=" + productType + "]";
	}

}
