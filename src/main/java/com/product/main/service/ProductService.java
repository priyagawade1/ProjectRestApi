package com.product.main.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.product.main.entity.Product;

public interface ProductService {
	
	public boolean saveProduct(Product product);
	public List<Product> getAllProduct();
	public Product getDataById(String productId);
	public boolean deleteProductById(String productId);
	public boolean updateProduct(Product product);
	public List<Product> getProductByName(String productName);
	public List<Product> getProductMaxPrise();
	public String uploadSheet(MultipartFile file, HttpSession httpSession);
	

}
