package com.kdu.cryptotrading.Comparators;

import com.kdu.cryptotrading.entities.Coin;

import java.util.Comparator;

public class CoinComparator implements Comparator<Coin> {
    @Override
    public int compare(Coin o1, Coin o2) {
        return Double.compare(o2.getPrice(), o1.getPrice());
    }
}
