package com.abcretail.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Author: Harish Goud Avali
 * Date :- 02/18/2019
 * */

import com.abcretail.api.model.ProductPrice;
public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {
	ProductPrice findById(int id) throws Throwable;
}
