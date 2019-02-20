package com.abcretail.api.client;

import java.io.IOException;

public interface ProductInfoClient {
    String getProductName(int id) throws IOException;
}
