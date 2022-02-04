package com.kdu.cryptotrading.entities.transactions;

import com.kdu.cryptotrading.CustomErrors.CustomException;

import java.util.ArrayList;

public class TransactionFactory {
    public static Transaction createTransaction(ArrayList<String> transactionData) throws CustomException {
        Type type = Type.valueOf(transactionData.get(0));
        String coin = transactionData.get(1);
        switch (type) {
            case BUY:
                double quantity = Double.parseDouble(transactionData.get(2));
                String walletAddress = transactionData.get(3);
                return new BuyTransaction(coin, quantity, walletAddress);
            case SELL:
                quantity = Double.parseDouble(transactionData.get(2));
                walletAddress = transactionData.get(3);
                return new SellTransaction(coin, quantity, walletAddress);
            case ADD_VOLUME:
                double volume = Double.parseDouble(transactionData.get(2));
                return new AddVolumeTransaction(coin, volume);
            case UPDATE_PRICE:
                double price = Double.parseDouble(transactionData.get(2));
                return new UpdatePriceTransaction(coin, price);
            default:
                throw new CustomException("Unknown Transaction type");
        }
    }
}
