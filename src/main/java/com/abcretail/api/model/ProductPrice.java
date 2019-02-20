package com.abcretail.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Author: Harish Goud Avali
 * Date :- 02/18/2019
 * */
@Document(collection="itemprice")
public class ProductPrice implements Serializable{



	@Id
	private int id;
	private BigDecimal price;
	private String currencyCode;
	
	
	public ProductPrice(){
	}


	@JsonIgnore
	@JsonProperty(value = "id")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "ProductPrice {"
			+ "price=" + price + ","
			+ "currencyCode =" + currencyCode + "}";
	}


	
}
