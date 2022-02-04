package com.kdu.cryptotrading.Comparators;

import com.kdu.cryptotrading.entities.Trader;

import java.util.Comparator;

public class StrugglingTraderComparator implements Comparator<Trader> {
    @Override
    public int compare(Trader o1, Trader o2) {
        return Double.compare(o1.getProfit(), o2.getProfit());
    }
}
