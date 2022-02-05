package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Update Price Transaction class
 * Inherits from Transaction class
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UpdatePriceTransaction extends Transaction {
    private double price;

    UpdatePriceTransaction(String coin, double price) {
        this.setType("UPDATE_PRICE");
        this.setCoinSymbol(coin);
        this.price = price;
    }
}
