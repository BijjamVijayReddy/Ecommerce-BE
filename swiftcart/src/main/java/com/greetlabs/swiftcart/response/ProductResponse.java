package com.greetlabs.swiftcart.response;

import java.util.Base64;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
	
    private int Id;
    private String ProductName;
    private double Price;
    private String photo;
    private int Discount;
    private String Category;
    private String Discription;
    public ProductResponse(int Id, String ProductName,  double Price, byte[] photoBytes,int Discount,String Category,String Discription) {
        this.Id = Id;
        this.ProductName = ProductName;
        this.Price = Price;
        this.photo = photoBytes != null ? Base64.getEncoder().encodeToString(photoBytes) : null;
        this.Discount=Discount;
        this.Category=Category;
        this.Discription=Discription;
    }


}
