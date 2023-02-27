package com.example.model;


import com.example.enums.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductStock {
    private final Map<Product, Integer> products = new HashMap<>();

    public ProductStock(Map<Product, Integer> products) {
        this.products.putAll(products);
    }

    public Map<Product, Integer> getProducts() {
        return new HashMap<>(products);
    }

    public void add(Product product) {
        Integer quantity = this.products.get(product);
        this.products.put(product,quantity++);
    }

    public void dispense(Product product) {
        Integer quantity = this.products.get(product);
        this.products.put(product,quantity--);
    }

    public boolean isProductInStock(Product product){
        return products.get(product) > 0;
    }
}
