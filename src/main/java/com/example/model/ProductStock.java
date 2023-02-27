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
        Integer new_quantity = this.products.get(product)+1;
        this.products.put(product,new_quantity);
    }

    public void dispense(Product product) {
        System.out.println(this.products.get(product));
        Integer new_quantity = this.products.get(product)-1;
        this.products.put(product,new_quantity);
        System.out.println(this.products.get(product));
    }

    public boolean isProductInStock(Product product){
        return products.get(product) > 0;
    }
}
