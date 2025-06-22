package com.github.katerynabratiuk.entity;

import java.math.BigDecimal;

public class Product implements Comparable<Product>{

    String name;
    Integer quantity;
    BigDecimal price;

    public Product(String name, Integer quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity >= 0)
        {
            this.quantity = quantity;
        } else
        throw new IllegalArgumentException("Quantity cannot be negative");
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) >= 0)
        {
            this.price = price;
        }
        throw new IllegalArgumentException("Quantity cannot be negative");
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
