package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;


@Data
public class Transaction {
    protected String type;
    protected String coinSymbol;
    private String blockHash;
}
