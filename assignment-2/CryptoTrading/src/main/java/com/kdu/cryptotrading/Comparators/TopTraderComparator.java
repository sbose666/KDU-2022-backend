package com.kdu.cryptotrading.Comparators;

import com.kdu.cryptotrading.entities.Trader;

import java.util.Comparator;

public class TopTraderComparator implements Comparator<Trader> {
    /**
     * Compares two traders based on their profit.
     * @param o1 first trader
     * @param o2 second trader
     * @return 1 if o1 has a lower profit than o2, -1 if o1 has a higher profit than o2, 0 if they have the same profit
     */
    @Override
    public int compare(Trader o1, Trader o2) {
        return Double.compare(o2.getProfit(), o1.getProfit());
    }
}
