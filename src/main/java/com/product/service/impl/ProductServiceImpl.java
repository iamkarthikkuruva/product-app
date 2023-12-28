package com.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.repo.ProductRepository;
import com.product.service.IProductService;
import com.product.util.ProductUtil;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository repo; // HAS-A

	public Integer saveProduct(Product p) {
		ProductUtil.calculateDetails(p);
		// save method returns same object with PK(ID) generated
		p = repo.save(p);
		return p.getProdId();
	}

	public void updateProduct(Product p) {
		// if id is null or not exist in DB then throw exception
		if (p.getProdId() == null || !repo.existsById(p.getProdId())) {
			throw new ProductNotFoundException("Product Can not be updated as it is not exist !!");
		}
		ProductUtil.calculateDetails(p);
		repo.save(p);
	}

	public void deleteProduct(Integer id) {
		// repo.deleteById(id);
		repo.delete(getOneProduct(id));
	}

	public Product getOneProduct(Integer id) {
		/*
		 * Optional<Product> opt = repo.findById(id); if(opt.isEmpty()) { throw new
		 * ProductNotFoundException("Product '"+id+"' Not Exist"); } else { return
		 * opt.get(); }
		 */
		return repo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product '" + id + "' Not Exist"));
	}

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Transactional
	public void updateProductCodeById(String code, Integer id) {
		if (!repo.existsById(id)) { // id not exist
			throw new ProductNotFoundException("Product '" + id + "' Not exist");
		}
		repo.updateProductCodeById(code, id);
	}

}
