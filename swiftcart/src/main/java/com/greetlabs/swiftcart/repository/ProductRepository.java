package com.greetlabs.swiftcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greetlabs.swiftcart.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
