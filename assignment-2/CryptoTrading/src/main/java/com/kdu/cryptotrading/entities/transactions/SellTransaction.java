package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Sell Transaction class
 * Inherits from Transaction class
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SellTransaction extends Transaction {
    private double quantity;
    private String walletAddress;

    SellTransaction(String coin, double quantity, String walletAddress) {
        this.setType("SELL");
        this.quantity = quantity;
        this.setCoinSymbol(coin);
        this.walletAddress = walletAddress;
    }
}
