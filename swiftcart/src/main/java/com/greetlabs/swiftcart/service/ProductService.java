package com.greetlabs.swiftcart.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greetlabs.swiftcart.entity.Product;
import com.greetlabs.swiftcart.response.ProductResponse;

import io.jsonwebtoken.io.IOException;

@Service
public interface ProductService {
	
	Product addProduct(MultipartFile file,String ProductName,double Price,int Discount,String Category,String Discription)throws SQLException,IOException, java.io.IOException, Exception;

	List<ProductResponse> getAllProducts();
	
//	byte[] getPhotoByProductId(int Id) throws SQLException;

	
}
