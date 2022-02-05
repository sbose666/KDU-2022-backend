package com.kdu.cryptotrading.entities;

import lombok.Data;

/**
 * Coin class containing information about a coin.
 */
@Data
public class Coin {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private double circulatingSupply;

    public Coin(int rank, String name, String symbol, double price, double circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }

    /**
     * Deep copy constructor for Coin class
     */
    public Coin(Coin coin) {
        this.rank = coin.getRank();
        this.name = coin.getName();
        this.symbol = coin.getSymbol();
        this.price = coin.getPrice();
        this.circulatingSupply = coin.getCirculatingSupply();
    }
}
