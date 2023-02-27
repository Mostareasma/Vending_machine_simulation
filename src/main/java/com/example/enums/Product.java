package com.example.enums;

public enum Product {
    MIRENDINA(2,5),
    TANGO(3,3),
    KITKAT(4,13),
    SNICKERS(7,15),
    MILKA(13,26);

    private int id;
    private double price;

    private Product(double price , int number) {
        this.price = price;
        this.id = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Product findByNumber(int number) {
        for (Product product : Product.values()) {
            if (product.getId() == number) {
                return product;
            }
        }
        return null;
    }
}
