package com.abcretail.api.service;

import java.io.IOException;

import org.springframework.web.client.HttpClientErrorException;

import com.mongodb.MongoException;
import com.abcretail.api.model.ProductDetails;

public interface ProductDetailsService {

	public ProductDetails getProductDetails(int id) throws MongoException,MongoException, HttpClientErrorException,IOException;
	public ProductDetails putProductDetails(int id,ProductDetails newProduct) throws Exception;
}
