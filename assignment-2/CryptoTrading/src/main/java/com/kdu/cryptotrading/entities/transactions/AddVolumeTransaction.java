package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class AddVolumeTransaction extends Transaction {
    private double volume;

    AddVolumeTransaction(String coin, double volume) {
        this.setType("ADD_VOLUME");
        this.setCoinSymbol(coin);
        this.volume = volume;
    }
}
