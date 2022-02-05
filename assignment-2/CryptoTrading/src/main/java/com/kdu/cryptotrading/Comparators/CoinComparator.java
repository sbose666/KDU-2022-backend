package com.kdu.cryptotrading.Comparators;

import com.kdu.cryptotrading.entities.Coin;

import java.util.Comparator;

public class CoinComparator implements Comparator<Coin> {
    /**
     * Compares two coins by their price.
     *
     * @param o1 first coin
     * @param o2 second coin
     * @return -1 if o2 is less than o1, 0 if they are equal, 1 if o2 is greater than o1
     */
    @Override
    public int compare(Coin o1, Coin o2) {
        return Double.compare(o2.getPrice(), o1.getPrice());
    }
}
