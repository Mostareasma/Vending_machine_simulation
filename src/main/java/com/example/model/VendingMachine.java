package com.example.model;

import com.example.enums.Coin;
import com.example.enums.Product;
import com.example.exception.InsufficientFundsException;
import com.example.exception.ProductNotFoundException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class VendingMachine {

// isOrderCanceled

    private CoinInventory coinInventory;
    private ProductStock productStock;

    public VendingMachine(CoinInventory coinInventory, ProductStock productStock) {
        this.coinInventory = coinInventory;
        this.productStock = productStock;
    }

    public void depositCoins(Map<Coin, Integer> givenCoins) {
        this.coinInventory.deposit(givenCoins);
    }

    public void withdrawCoins(Map<Coin, Integer> givenCoins) {
        this.coinInventory.withdraw(givenCoins);
    }

    public double calculateValue(Map<Coin, Integer> givenCoins) {
        double total = 0;
        for (Coin coin : givenCoins.keySet()) {
            total += coin.getValue() * givenCoins.get(coin);
        }
        return total;
    }

    public Map<Coin, Integer> refundCoins(Map<Coin, Integer> givenCoins) {
        return givenCoins;
    }

    // function that check isOrderCAceled

    public Map<Coin, Integer> purchase(Map<Coin, Integer> givenCoins, int productId)
            throws ProductNotFoundException, InsufficientFundsException {

        double paidAmount = calculateValue(givenCoins);

        Product product = Product.findByNumber(productId);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        if (!productStock.isProductInStock(product)) {
            throw new InsufficientFundsException("Product out of stock");
        }

        if (product.getPrice() > paidAmount) {
            throw new InsufficientFundsException("Paid amount insufficient");
        }

        productStock.dispense(product);

        depositCoins(givenCoins);

        double changeValue = paidAmount - product.getPrice();

        // counting the change
        Map<Coin, Integer> changeCoins = new HashMap<>();

        int coinCount = (int) (changeValue / 10);
        if (coinCount > 0 && coinInventory.getCoins().get(Coin.COIN_10) > 0 ){
            changeCoins.put(Coin.COIN_10, coinCount);
            changeValue -= coinCount * 10;
        }

        coinCount = (int) (changeValue / 5);
        if (coinCount > 0 && coinInventory.getCoins().get(Coin.COIN_5) > 0 ){
            changeCoins.put(Coin.COIN_5, coinCount);
            changeValue -= coinCount * 5;
        }

        coinCount = (int) (changeValue / 2);
        if (coinCount > 0 && coinInventory.getCoins().get(Coin.COIN_2) > 0 ){
            changeCoins.put(Coin.COIN_2, coinCount);
            changeValue -= coinCount * 2;
        }

        coinCount = (int) (changeValue / 1);
        if (coinCount > 0 && coinInventory.getCoins().get(Coin.COIN_1) > 0 ){
            changeCoins.put(Coin.COIN_1, coinCount);
            changeValue -= coinCount * 1;
        }

        coinCount = (int) (changeValue / 0.1);
        if (coinCount > 0 && coinInventory.getCoins().get(Coin.COIN_0_POINT_5) > 0 ){
            changeCoins.put(Coin.COIN_0_POINT_5, coinCount);
            changeValue -= coinCount * 0.1;
        }

        withdrawCoins(changeCoins);

        return changeCoins;
    }

}
