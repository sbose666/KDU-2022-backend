package com.kdu.cryptotrading.entities.transactions.processor;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.kdu.cryptotrading.entities.TraderLibrary;
import com.kdu.cryptotrading.entities.transactions.BuyTransaction;
import com.kdu.cryptotrading.gui.LogGUI;


public class BuyTransactionProcessor {
    /**
     * Processes a buy transaction.
     * Checks if there are enough coins to buy.
     *
     * @param transaction The buy transaction to process.
     */
    public static void processTransaction(BuyTransaction transaction) {
        new Thread(() -> {
            String traderName = TraderLibrary.getTrader(transaction.getWalletAddress()).getFirstName() + " " + TraderLibrary.getTrader(transaction.getWalletAddress()).getLastName();
            // Check for sufficient circulating supply of the coin and wait if not available
            while (CoinLibrary.getCurrentCoinSupply(transaction.getCoinSymbol()) < transaction.getQuantity()) ;
            try {
                CoinLibrary.decreaseVolume(transaction.getCoinSymbol(), transaction.getQuantity());
            } catch (CustomException e) {
                LogGUI.jTextArea.append(e.getMessage() + '\n');
            } finally {
                try {
                    TraderLibrary.updateProfileOfTrader(transaction.getWalletAddress(), transaction.getCoinSymbol(), transaction.getQuantity(), "BUY");
                } catch (CustomException e) {
                    LogGUI.jTextArea.append(e.getMessage() + '\n');
                }
                LogGUI.jTextArea.append("Trader: " + traderName + " has bought " + transaction.getQuantity() + " amount of coin: " + transaction.getCoinSymbol() + '\n');
            }
        }).start();
    }
}
