package com.abcretail.api.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class ProductInfoClientImpl implements ProductInfoClient {
    private final Logger log = Logger.getLogger(ProductInfoClientImpl.class.getName());
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment env;

    @Override
    public String getProductName(int id) throws IOException {
        log.info("in getProductName");
        String url = env.getProperty("target.restUrl1") + id + env.getProperty("target.restUrl2");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        String productName = "";
        try {
            JsonNode root = null;
            String jsonString = response.getBody();
            if (jsonString != null || !"".equals(jsonString)) {
                root = mapper.readTree(jsonString);
                if (root.findValue("product") != null) {
                    root = root.findValue("product");
                    if (root.findValue("item") != null) {
                        root = root.findValue("item");
                        if (root.findValue("product_description") != null) {
                            root = root.findValue("product_description");
                            if (root.findValue("title") != null) {
                                productName = root.findValue("title").asText();
                            }
                        }
                    }
                }
            }
            log.debug("productName : " + productName);
        } catch (IOException e) {
            log.error("Parsing failed IOException " + e.getMessage());
            throw new IOException(e.getMessage());
        }
        return productName;
    }
}
