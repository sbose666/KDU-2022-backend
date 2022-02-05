package com.kdu.cryptotrading.entities.transactions;

import lombok.Data;

/**
 * Transaction base class
 */
@Data
public class Transaction {
    protected String type;
    protected String coinSymbol;
    private String blockHash;
}
