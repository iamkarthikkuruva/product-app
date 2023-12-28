package com.product.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ProductNotFoundException;
import com.product.model.Product;
import com.product.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/product")
@Api(description = "SAMPLE TEST APPLICATION FOR PRODUCT")
public class ProductRestController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductRestController.class);

	@Autowired
	private IProductService service;// HAS-A

	// 1. save
	@PostMapping("/create")
	@ApiOperation("SAVE PRODUCT DATA")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {

		LOG.info("ENTERED INTO SAVE METHOD");
		ResponseEntity<String> resp = null;
		try {
			Integer id = service.saveProduct(product);
			String body = "Product '" + id + "' created!";
			resp = new ResponseEntity<String>(body, HttpStatus.CREATED);
			LOG.debug(body);
		} catch (Exception e) {
			LOG.error("SAVING IS FAILED  {}", e.getMessage());
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to process request : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("ABOUT TO LEAVE SAVE METHOD");
		return resp;
	}

	// 2. get all
	@GetMapping("/all")
	@ApiOperation("FETCH ALL PRODUCTS DATA")
	public ResponseEntity<?> getAllProducts() {

		ResponseEntity<?> resp = null;
		try {
			List<Product> list = service.getAllProducts();
			resp = new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>("Unable to process request : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	// 3. get one
	@GetMapping("/find/{id}")
	@ApiOperation("FETCH ONE PRODUCT DATA BY ID")
	public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {
		LOG.info("ENTERED INTO FETCH ONE BY ID");
		ResponseEntity<?> resp = null;
		try {
			Product pob = service.getOneProduct(id);
			resp = new ResponseEntity<Product>(pob, HttpStatus.OK);
			LOG.debug("DATA FOUND WITH ID {}", id);
		} catch (ProductNotFoundException e) {
			LOG.error("PROBLEM IS : {}", e.getMessage());
			e.printStackTrace();
			throw e;
		}

		LOG.info("ABOUT TO LEAVE FETCH ONE BY ID");
		return resp;
	}

	// 4. delete
	@DeleteMapping("/remove/{id}")
	@ApiOperation("DELETE ONE PRODUCT DATA BY ID")
	public ResponseEntity<String> deleteOneProduct(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			service.deleteProduct(id);
			resp = new ResponseEntity<String>("Product removed " + id, HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			throw e;
		}

		return resp;
	}

	// 5. update
	@PutMapping("/modify")
	@ApiOperation("UPDATE ONE PRODUCT DATA BY ID")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			service.updateProduct(product);
			resp = new ResponseEntity<String>("Product '" + product.getProdId() + "' Updated", HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			throw e;
		}

		return resp;
	}

	@PatchMapping("/update/{id}/{code}")
	@ApiOperation("MODIFY ONE PRODUCT CODE BY ID")
	public ResponseEntity<String> updateProductCode(@PathVariable Integer id, @PathVariable String code) {
		ResponseEntity<String> resp = null;
		try {
			service.updateProductCodeById(code, id);
			resp = new ResponseEntity<String>("Product '" + id + "' Updated with code :" + code, HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			throw e;
		}

		return resp;
	}
}
