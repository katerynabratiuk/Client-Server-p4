package com.github.katerynabratiuk.service;

import com.github.katerynabratiuk.entity.Product;

public interface InventoryService {

    void addNewProduct(Product product);
    void removeBatch(String productName, Integer quantity);
    void addBatch(String productName, Integer quantity);

}
