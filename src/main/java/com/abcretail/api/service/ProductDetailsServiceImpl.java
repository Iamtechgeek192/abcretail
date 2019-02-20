package com.abcretail.api.service;

import java.io.IOException;
import java.sql.SQLException;

import com.abcretail.api.repository.ProductPriceRepository;
import com.abcretail.api.client.ProductInfoClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;
import com.abcretail.api.model.ProductDetails;
import com.abcretail.api.model.ProductPrice;

@Service(value = "productDetailService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private final Logger log = Logger.getLogger(ProductDetailsServiceImpl.class.getName());

    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ProductInfoClient productInfoClient;



    @Override
    public ProductDetails getProductDetails(int id) throws MongoException, IOException {
        log.info("in  getProductDetails ");
        log.debug("id: " + id);
        String productName = productInfoClient.getProductName(id);
        log.debug("productName: " + productName);
        ProductPrice prodPrice = getProductPrice(id);
        if (prodPrice == null) {
            log.error("price detail null mongo exception thrown");
            throw new MongoException("price details for product with id=" + id + " not found in mongo db for collection productprice");
        }
        ProductDetails prodDetails = new ProductDetails(id, productName, prodPrice);
        log.debug("prodDetails: " + prodDetails);
        return prodDetails;
    }

    @Override
    public ProductDetails putProductDetails(int id, ProductDetails newProduct) throws Exception {
        log.info("in putProductDetails");
        log.debug(" newProduct : " + newProduct);
        if (id != newProduct.getId()) {
            throw new Exception(" Product price cannot be updated, request body json should have matching id with path variable.");
        }
        ProductPrice newProductPrice = newProduct.getProductPrice();
        if (newProduct.getProductPrice().getCurrencyCode() == null || newProduct.getProductPrice().getPrice() == null) {
            throw new Exception(" Please check product price and currency code details, it should not be empty ");
        }
        newProductPrice.setId(id);
        String productName = productInfoClient.getProductName(id);
        newProduct.setName(productName);
        newProductPrice = updateProductPrice(id, newProduct);

        newProduct.setProductPrice(newProductPrice);
        return newProduct;
    }

    public ProductPrice getProductPrice(int id) throws MongoException {
        log.info("in getProductPrice");
        log.debug("id : " + id);
        ProductPrice prodPrice = null;
        try {
            prodPrice = productPriceRepository.findById(id);
        } catch (Throwable exception ){
            exception.printStackTrace();
        }
        log.debug("prodPrice : " + prodPrice);
        return prodPrice;
    }

    public ProductPrice updateProductPrice(int id, ProductDetails newProduct) throws MongoException {
        log.info("in updateProductPrice");
        ProductPrice newProductPrice = newProduct.getProductPrice();
        newProductPrice.setId(id);
        ProductPrice prodPrice = null;
        try {
            prodPrice = productPriceRepository.findById(newProductPrice.getId());
        } catch (Throwable e){
            e.printStackTrace();
        }
        if (prodPrice != null) {
            newProductPrice = productPriceRepository.save(newProduct.getProductPrice());
        } else {
            log.error("price detail null mongo exception thrown");
            throw new MongoException("price details for product with id=" + id + " not found in mongo db for collection 'productprice'");
        }
        log.debug("newProductPrice : " + newProductPrice);
        return newProductPrice;
    }


}
