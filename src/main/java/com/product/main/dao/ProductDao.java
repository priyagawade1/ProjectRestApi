package com.product.main.dao;

import java.util.List;

import com.product.main.entity.Product;

public interface ProductDao {
	
	public boolean saveProduct(Product product);
	public List<Product> getAllProduct();
	public Product getDataById( String productId);
	public boolean deleteProductById(String productId);
	public boolean updateProduct(Product product);
	public List<Product> getProductByName(String productName);
	public List<Product> getProductMaxPrise();
	

}
