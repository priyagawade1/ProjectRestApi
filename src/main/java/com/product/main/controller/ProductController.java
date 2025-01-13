package com.product.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.product.main.entity.Product;
import com.product.main.exception.ProductNotFoundException;
import com.product.main.exception.ProductNotSaveException;
import com.product.main.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {
		boolean isAdded = productService.saveProduct(product);

		if (isAdded == true) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}

		else {
			// return new ResponseEntity<Boolean>(isAdded,
			// HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ProductNotSaveException("product not save :" + product);
		}

	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = productService.getAllProduct();

		if (allProduct != null) {
			return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Products not found :");
			// return new ResponseEntity<List<Product>>(allProduct, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getProductById")
	public ResponseEntity<Product> getProductById(@RequestParam String productId) {
		Product productById = productService.getDataById(productId);
		if (productById != null) {
			return new ResponseEntity<Product>(productById, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("product not found with this id :" + productId);
		}

	}

	@GetMapping("/getProductByName")
	public ResponseEntity<List<Product>> getDataByName(@RequestParam String productName) {

		List<Product> productByName = productService.getProductByName(productName);
		if (productByName.isEmpty()) {
			return new ResponseEntity<List<Product>>(productByName, HttpStatus.OK);

		} else {
			// return new ResponseEntity<List<Product>>(productByName,HttpStatus.NOT_FOUND);
			throw new ProductNotFoundException("Product not found with this name : " + productName);
		}

	}

	@DeleteMapping("/deleteProductById")
	public ResponseEntity<Boolean> ProdductDeleteById(@RequestParam String productId) {
		boolean isDelete = productService.deleteProductById(productId);

		if (isDelete) {
			return new ResponseEntity<Boolean>(isDelete, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product not found with this id : " + productId);
		}
	}

	@PutMapping("/productUpdate")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean updateProductResult = productService.updateProduct(product);

		if (updateProductResult) {
			return new ResponseEntity<Boolean>(updateProductResult, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product not found with this id : " + product);
		}

	}

	@GetMapping("/getMaxProduct")
	public ResponseEntity<List<Product>> getMaxProductss() {
		List<Product> productMaxPrise = productService.getProductMaxPrise();
		if (productMaxPrise != null) {
			return new ResponseEntity<List<Product>>(productMaxPrise, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product not found  :");
		}

	}
	
	@PostMapping("/sheetUpload")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file,HttpSession session)
	{
		//System.out.println(file.getOriginalFilename());
		
		productService.uploadSheet(file, session);
		
		
		return null;
		
	}
	

}
