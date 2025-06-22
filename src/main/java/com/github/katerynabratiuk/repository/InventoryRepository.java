package com.github.katerynabratiuk.repository;

import com.github.katerynabratiuk.entity.Product;

public interface InventoryRepository {

    void addNewProduct(Product product);
    void removeBatch(String productName, Integer quantity);
    void addBatch(String productName, Integer quantity);
    Integer getCurrentStockSize();
    Product getProduct(String name);
}
