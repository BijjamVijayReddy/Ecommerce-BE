package com.greetlabs.swiftcart.controller;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.greetlabs.swiftcart.entity.Product;
import com.greetlabs.swiftcart.exception.PhotoRetrievalException;
import com.greetlabs.swiftcart.exception.ResourceNotFoundException;
import com.greetlabs.swiftcart.response.ProductResponse;
import com.greetlabs.swiftcart.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	 public ResponseEntity<?> addProduct(
	            @RequestParam("Photo") MultipartFile Photo,
	            @RequestParam("ProductName") String ProductName,
	            @RequestParam("Price") double Price,
	            @RequestParam("Discount") int Discount,
	            @RequestParam("Category") String Category,
	            @RequestParam("Discription") String Discription) throws Exception {
		
		 try {
	            Product product = service.addProduct(Photo, ProductName, Price, Discount, Category, Discription);
	            return ResponseEntity.ok("Product saved with ID: " + product.getId());
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error saving product: " + e.getMessage());
	        }
	    	
//		Product savedProduct = service.addProduct(Photo, ProductName, Price,Discount,Category,Discription);
//	        ProductResponse response=new ProductResponse(savedProduct.getId(),savedProduct.getProductName(),savedProduct.getPrice());
//			return ResponseEntity.ok(savedProduct);
	        
	    }
	
	
	@GetMapping("/all-products")
	 public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

	
	@GetMapping("{Id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable int Id){
		
		Optional<Product> pro1=service.getProductById(Id);
		
		return pro1.map(product -> {
            // Convert Product to ProductResponse
            ProductResponse productResponse = getProductResponse(product);
            return productResponse;  // Return ProductResponse
        })
        .map(ResponseEntity::ok)  // Wrap the ProductResponse in ResponseEntity.ok()
        .orElseThrow(() -> new  ResourceNotFoundException("Product Not Found"));
		
	}


	private ProductResponse getProductResponse(Product product) {
		byte[] photoBytes=null;
		Blob photoBlob=product.getPhoto();
		if(photoBlob!=null) {
			try {
				photoBytes=photoBlob.getBytes(1, (int) photoBlob.length());
			}
			catch(SQLException e) {
				throw new PhotoRetrievalException("Error retrieving photo");
			}
		}
		return new ProductResponse(product.getId(),product.getProductName(),product.getPrice(),
				photoBytes, product.getDisocunt(),product.getCategory(),product.getDiscription());
	}
}
