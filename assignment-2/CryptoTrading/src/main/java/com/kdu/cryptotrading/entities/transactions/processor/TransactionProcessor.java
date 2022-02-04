package com.kdu.cryptotrading.entities.transactions.processor;


import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.kdu.cryptotrading.entities.transactions.*;

import java.util.Random;

public class TransactionProcessor {
    public static void processTransaction(Transaction transaction) throws CustomException {
        Type type = Type.valueOf(transaction.getType());
        transaction.setBlockHash(getBlockHash());
        switch (type) {
            case BUY:
                BuyTransactionProcessor.processTransaction((BuyTransaction) transaction);
                break;
            case SELL:
                SellTransactionProcessor.processTransaction((SellTransaction) transaction);
                break;
            case ADD_VOLUME:
                CoinLibrary.addVolume(transaction.getCoinSymbol(), ((AddVolumeTransaction) transaction).getVolume());
                break;
            case UPDATE_PRICE:
                CoinLibrary.updatePrice(transaction.getCoinSymbol(), ((UpdatePriceTransaction) transaction).getPrice());
                break;
        }
    }

    private static String getBlockHash() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder transactionHash = new StringBuilder();
        Random rnd = new Random();
        for (double i = 0; i < 199999999; i++) {
            i = i;
        }
        while (transactionHash.length() < 128) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            transactionHash.append(SALTCHARS.charAt(index));
        }
        String hashCode = transactionHash.toString();
        return "0x" + hashCode.toLowerCase();
    }
}
