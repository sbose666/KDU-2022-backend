package com.kdu.cryptotrading.Comparators;

import com.kdu.cryptotrading.entities.Trader;

import java.util.Comparator;

public class TopTraderComparator implements Comparator<Trader> {
    @Override
    public int compare(Trader o1, Trader o2) {
        return Double.compare(o2.getProfit(), o1.getProfit());
    }
}
