package com.github.katerynabratiuk.repository.implementation;

import com.github.katerynabratiuk.entity.Product;
import com.github.katerynabratiuk.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// simplified repository
public class InventoryRepositoryImpl implements InventoryRepository {

    private Integer CAPACITY = 1000;

    private volatile int currentStock;

    private final List<Product> inventory;

    public InventoryRepositoryImpl()
    {
        this.inventory = new ArrayList<>();
        this.currentStock = 0;
    }

    public Integer getCapacity() {return CAPACITY;}

    @Override
    public void addNewProduct(Product product) {
       inventory.add(product);
       currentStock += product.getQuantity();
    };


    @Override
    public void addBatch(String productName, Integer quantity) {
        Product product = getProduct(productName);
        product.setQuantity(product.getQuantity() + quantity);
        System.out.println("Before: " + getCurrentStockSize());
        currentStock += quantity;
        System.out.println("After: " + getCurrentStockSize());
    }

    @Override
    public Integer getCurrentStockSize() {
        return this.currentStock;
    };

    @Override
    public void removeBatch(String productName, Integer quantity) {
        Product product = getProduct(productName);
        product.setQuantity(product.getQuantity() - quantity);
        currentStock -= quantity;
    };

    @Override
    public Product getProduct(String name)
    {
        for(Product product : inventory)
        {
            if (Objects.equals(product.getName(), name))
            {
                return product;
            }
        }
        return null;
    }

}
