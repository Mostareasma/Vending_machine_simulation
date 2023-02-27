package com.example.model;

import com.example.enums.Coin;
import com.example.enums.Product;
import com.example.exception.InsufficientFundsException;
import com.example.exception.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private int num_coin_1_point_5 = 0;
    private int num_coin_1 = 0;
    private int num_coin_2 = 0;
    private int num_coin_5 = 0;
    private int num_coin_10 = 0;

    private int num_mirendina = 0;
    private int num_tango = 0;
    private int num_snickers = 0;
    private int num_kitkat = 0;
    private int num_milka = 0;

    public VendingMachine(int num_coin_1_point_5, int num_coin_1, int num_coin_2, int num_coin_5, int num_coin_10, int num_mirendina, int num_tango, int num_kitkat, int num_snickers, int num_milka) {
        this.num_coin_1_point_5 = num_coin_1_point_5;
        this.num_coin_1 = num_coin_1;
        this.num_coin_2 = num_coin_2;
        this.num_coin_5 = num_coin_5;
        this.num_coin_10 = num_coin_10;
        this.num_mirendina = num_mirendina;
        this.num_milka = num_milka;
        this.num_tango= num_tango;
        this.num_snickers = num_snickers;
        this.num_kitkat = num_kitkat;
    }

    public void depositCoins(Coin[] givenCoins) {
        for (Coin coin : givenCoins) {
            switch (coin) {
                case COIN_1_POINT_5:
                    num_coin_1_point_5++;
                    break;
                case COIN_1:
                    num_coin_1++;
                    break;
                case COIN_2:
                    num_coin_2++;
                    break;
                case COIN_5:
                    num_coin_5++;
                    break;
                case COIN_10:
                    num_coin_10++;
                    break;
            }
        }
    }

    public void withdrawCoins(Coin[] coins) {
        for (Coin coin : coins) {
            switch (coin) {
                case COIN_1_POINT_5:
                    num_coin_1_point_5--;
                    break;
                case COIN_1:
                    num_coin_1--;
                    break;
                case COIN_2:
                    num_coin_2--;
                    break;
                case COIN_5:
                    num_coin_5--;
                    break;
                case COIN_10:
                    num_coin_10--;
                    break;
            }
        }
    }
    public Coin[] refundCoins(Coin[] givenCoins){
        return givenCoins;
    }

    public Coin[] purchase(Coin[] givenCoins, int productId) throws ProductNotFoundException, InsufficientFundsException {


        double paidAmount = 0;

        Product product = Product.findByNumber(productId);

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        for (Coin coin : givenCoins){
            paidAmount += coin.getValue();
        }

        dispenseProduct(product, paidAmount);

        depositCoins(givenCoins);

        double changeValue = paidAmount - product.getPrice();

        // counting the change
        List<Coin> changeCoins = new ArrayList<>();

        while (changeValue > 0) {
            if (changeValue >= 10) {
                changeCoins.add(Coin.COIN_10);
                changeValue -= 10;
            } else if (changeValue >= 5) {
                changeCoins.add(Coin.COIN_5);
                changeValue -= 5;
            } else if (changeValue >= 2) {
                changeCoins.add(Coin.COIN_2);
                changeValue -= 2;
            } else if (changeValue >= 1) {
                changeCoins.add(Coin.COIN_1);
                changeValue -= 1;
            } else if (changeValue >= 1.5) {
                changeCoins.add(Coin.COIN_1_POINT_5);
                changeValue -= 1.5;
            }
        }

        return changeCoins.toArray(new Coin[changeCoins.size()]);
    }

    public void dispenseProduct(Product product, double paidAmount) throws InsufficientFundsException {
        switch (product) {
            case MIRENDINA:
                if (num_mirendina == 0) {
                    throw new InsufficientFundsException("Product out of stock");
                } else if (paidAmount < product.getPrice()) {
                    throw new InsufficientFundsException("Insufficient funds product price is: " + product.getPrice());
                }else {
                    num_mirendina--;
                    paidAmount -= product.getPrice();
                }
                break;
            case TANGO:

                if (num_tango == 0) {
                    throw new InsufficientFundsException("Product out of stock");
                } else if (paidAmount < product.getPrice()) {
                    throw new InsufficientFundsException("Insufficient funds product price is: " + product.getPrice());
                }else {
                    num_tango--;
                    paidAmount -= product.getPrice();
                }
                break;
            case KITKAT:

                if (num_kitkat == 0) {
                    throw new InsufficientFundsException("Product out of stock");
                } else if (paidAmount < product.getPrice()) {
                    throw new InsufficientFundsException("Insufficient funds product price is: " + product.getPrice());
                }else {
                    num_kitkat--;
                    paidAmount -= product.getPrice();
                }
                break;
            case SNICKERS:

                if (num_snickers == 0) {
                    throw new InsufficientFundsException("Product out of stock");
                } else if (paidAmount < product.getPrice()) {
                    throw new InsufficientFundsException("Insufficient funds product price is: " + product.getPrice());
                }else {
                    num_snickers--;
                    paidAmount -= product.getPrice();
                }
                break;
            case MILKA:

                if (num_milka == 0) {
                    throw new InsufficientFundsException("Product out of stock");
                } else if (paidAmount < product.getPrice()) {
                    throw new InsufficientFundsException("Insufficient funds product price is: " + product.getPrice());
                }else {
                    num_milka--;
                    paidAmount -= product.getPrice();
                }
                break;
        }

    }

}
