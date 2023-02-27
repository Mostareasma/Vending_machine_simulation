package com.example.model;

import com.example.enums.Coin;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CoinInventory {
    private final Map<Coin, Integer> coins = new HashMap<>();

    public CoinInventory(Map<Coin, Integer> coins) {
        this.coins.putAll(coins);
    }

    public Map<Coin, Integer> getCoins() {
        return new HashMap<>(coins);
    }

    public void deposit(Map<Coin, Integer> coins) {
        coins.forEach((coin, quantity) -> {
            this.coins.merge(coin, quantity, Integer::sum);
        });
    }

    public boolean withdraw(Map<Coin, Integer> coins) {
        coins.forEach((coin, quantity) -> {
            this.coins.merge(coin, -quantity, Integer::sum);
        });

        return true;
    }

    

}
