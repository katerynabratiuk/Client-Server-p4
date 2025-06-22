package com.github.katerynabratiuk.service.implementation;

import com.github.katerynabratiuk.entity.Product;
import com.github.katerynabratiuk.repository.implementation.InventoryRepositoryImpl;
import com.github.katerynabratiuk.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepositoryImpl inventoryRepository;

    public InventoryServiceImpl(InventoryRepositoryImpl inventoryRepository)
    {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addNewProduct(Product product) {
        synchronized (inventoryRepository) {
            if (inventoryRepository.getCurrentStockSize() + product.getQuantity() <= inventoryRepository.getCapacity()) {
                inventoryRepository.addNewProduct(product);
            } else throw new IllegalArgumentException("Not enough space5.");
        }
    }

    @Override
    public void removeBatch(String productName, Integer quantity) {
        synchronized (inventoryRepository)
        {
            Product product = inventoryRepository.getProduct(productName);
            if (product.getQuantity() - quantity >= 0)
            {
                inventoryRepository.removeBatch(productName, quantity);
            }
            else throw new IllegalArgumentException("Not enough batches accessible.");
        }
    }

    @Override
    public void addBatch(String productName, Integer quantity) {
        synchronized (inventoryRepository)
        {
            if (inventoryRepository.getCurrentStockSize() + quantity <= inventoryRepository.getCapacity())
            {
                inventoryRepository.addBatch(productName, quantity);
            }
            else throw new IllegalArgumentException("Not enough space.");
        }

    }

}
