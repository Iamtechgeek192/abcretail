package com.abcretail.api.controller;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.mongodb.MongoException;
import com.abcretail.api.model.ProductDetails;
import com.abcretail.api.service.ProductDetailsService;
/**
 * Author: Harish Goud Avali
 * Date :- 02/18/2019
 * */
@RestController
@RequestMapping("/")
public class ProductDetailsController {

	private final Logger log = Logger.getLogger(ProductDetailsController.class.getName());
	
	@Autowired
	@Qualifier(value="productDetailService")
	ProductDetailsService productDetailsService;
	
	
	@RequestMapping(value="product/{id}",method=RequestMethod.GET)
	public ProductDetails getProductDetails(@PathVariable int id) throws HttpClientErrorException, MongoException, IOException{
		log.info("in controller getProductDetails id :"+id);
		ProductDetails prodDetails=null;
		prodDetails=productDetailsService.getProductDetails(id);
		log.info(" return productDetails :"+prodDetails);
		return prodDetails;
	}
	
	@RequestMapping(value="product/{id}",method=RequestMethod.PUT)
	public ProductDetails putProductDetails(@PathVariable int id,@RequestBody ProductDetails prodDetails) throws Exception{
		log.info("in controller putProductDetails id :"+id);
		log.info("in controller putProductDetails requestBody :"+prodDetails);
		ProductDetails updatedProductDetails=productDetailsService.putProductDetails(id, prodDetails);
		log.info(" updated putProductDetails :"+updatedProductDetails);
		return updatedProductDetails;
	}
}
