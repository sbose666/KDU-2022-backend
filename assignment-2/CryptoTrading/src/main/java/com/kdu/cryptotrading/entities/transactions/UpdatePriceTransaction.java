package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UpdatePriceTransaction extends Transaction {
    private double price;

    UpdatePriceTransaction(String coin, double price) {
        this.setType("UPDATE_PRICE");
        this.setCoinSymbol(coin);
        this.price = price;
    }
}
