package com.product.main.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.product.main.dao.ProductDao;
import com.product.main.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public boolean saveProduct(Product product) {
		
		boolean isAdded=false;
		if(product.getProductId()== null)
		{
			String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
			product.setProductId(id);
			isAdded = productDao.saveProduct(product);

			return isAdded=true;
		}
		else
		{
			return isAdded;
		}
			
			
			
		
	}

	@Override
	public List<Product> getAllProduct() {

		List<Product> list = productDao.getAllProduct();

		return list;
	}

	@Override
	public Product getDataById(String productId) {
		Product dataById = productDao.getDataById(productId);
		return dataById;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDelete = productDao.deleteProductById(productId);

		return isDelete;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean updateProductStatus = productDao.updateProduct(product);

		return updateProductStatus;
	}

	@Override
	public List<Product> getProductByName(String productName) {
		List<Product> productByName = productDao.getProductByName(productName);

		return productByName;
	}

	@Override
	public List<Product> getProductMaxPrise() {
		
	List<Product> productMaxPrise = productDao.getProductMaxPrise();
		return productMaxPrise;
	}

	@Override
	public String uploadSheet(MultipartFile file, HttpSession httpSession) {
		
		String path = httpSession.getServletContext().getRealPath("/");
		
		String filename = file.getOriginalFilename();
		FileOutputStream fos=null;
		
	//	byte[] data = file.getBytes();
		
		try {
			byte[] data = file.getBytes();
			System.out.println(path);
			fos= new FileOutputStream(new File(path+ File.separator+ filename));
			fos.write(data);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		
		return null;
	}

}
