package com.greetlabs.swiftcart.service;

import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greetlabs.swiftcart.entity.Product;

import com.greetlabs.swiftcart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repo;

	@Override
	public Product addProduct(MultipartFile file, String ProductName, double Price, int Discount, String Category,
			String Discription) throws Exception {
		Product product=new Product();
		product.setProductName(ProductName);
		product.setPrice(Price);
		product.setDisocunt(Discount);
		product.setCategory(Category);
		product.setDiscription(Discription);
		if (!file.isEmpty()) {
	        byte[] photoBytes = file.getBytes();
	        Blob photoBlob = new SerialBlob(photoBytes);
	        product.setPhoto(photoBlob);
	    }

	    // Save the product
	    Product savedProduct = repo.save(product);
	    
	    // Convert Blob to base64 image string
	    Blob photoblob = savedProduct.getPhoto();
	    byte[] imageBytes = convertBlobToBytes(photoblob);
	    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	    
	    System.out.println("Base64 Encoded Image: " + base64Image);
	    
	    return savedProduct; // Returning the saved product, you can return just ID if preferred
	}
		
		
//		 // Convert Blob to byte array
//        byte[] imageBytes = convertBlobToBytes(photoblob);
//        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//        System.out.println("Base64 Encoded Image: " + base64Image);
//		
//	
//	}
//	
	 public static byte[] convertBlobToBytes(Blob blob) throws Exception {
	        int blobLength = (int) blob.length();  
	        return blob.getBytes(1, blobLength);  // 1-based offset
	 }
//	 
//	 // Method to decode Base64 string to byte array and save as Blob
//	    public Product updateProductPhotoFromBase64(int productId, String base64Image) throws Exception {
//	        Product product = repo.findById(productId)
//	                              .orElseThrow(() -> new Exception("Product not found"));
//
//	        // Decode Base64 string back to byte array
//	        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//
//	        // Convert byte array to Blob and update the product
//	        Blob imageBlob = new SerialBlob(imageBytes);
//	        product.setPhoto(imageBlob);
//
//	        return repo.save(product);
//	    }


	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

}
