package com.product.service;

import java.util.List;

import com.product.model.Product;

public interface IProductService {

	Integer saveProduct(Product p);
	void updateProduct(Product p);
	void deleteProduct(Integer id);
	
	Product getOneProduct(Integer id);
	List<Product> getAllProducts();
	
	void updateProductCodeById(String code,Integer id);
	
}
