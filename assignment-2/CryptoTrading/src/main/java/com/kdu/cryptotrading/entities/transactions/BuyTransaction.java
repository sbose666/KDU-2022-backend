package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Buy Transaction class
 * Inherits from Transaction class
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BuyTransaction extends Transaction {
    private double quantity;
    private String walletAddress;

    BuyTransaction(String coin, double quantity, String walletAddress) {
        this.setType("BUY");
        this.quantity = quantity;
        this.setCoinSymbol(coin);
        this.walletAddress = walletAddress;
    }

}
