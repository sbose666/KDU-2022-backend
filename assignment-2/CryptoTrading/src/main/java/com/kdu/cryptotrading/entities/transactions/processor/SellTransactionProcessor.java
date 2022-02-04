package com.kdu.cryptotrading.entities.transactions.processor;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.kdu.cryptotrading.entities.TraderLibrary;
import com.kdu.cryptotrading.entities.transactions.SellTransaction;
import com.kdu.cryptotrading.gui.LogGUI;

public class SellTransactionProcessor {
    public static void processTransaction(SellTransaction transaction) {
        new Thread(() -> {
            String traderName = TraderLibrary.getTrader(transaction.getWalletAddress()).getFirstName() + " " + TraderLibrary.getTrader(transaction.getWalletAddress()).getLastName();
            // Check for sufficient availability of the coin for the concerned trader
            if (!TraderLibrary.getTrader(transaction.getWalletAddress()).traderHasCoin(transaction.getCoinSymbol())) {
                LogGUI.jTextArea.append("No coin: " + transaction.getCoinSymbol() + " in trader: " + traderName + "'s profile\n");
                return;
            }
            if (TraderLibrary.getTrader(transaction.getWalletAddress()).getCoinQuantityInCryptoProfile(transaction.getCoinSymbol()) < transaction.getQuantity()) {
                LogGUI.jTextArea.append(traderName + " doesn't have enough coin: " + transaction.getCoinSymbol() + "\nAvailable: " + TraderLibrary.getTrader(transaction.getWalletAddress()).getCoinQuantityInCryptoProfile(transaction.getCoinSymbol()) + " Required: " + transaction.getQuantity() + '\n');
                return;
            }
            try {
                CoinLibrary.addVolume(transaction.getCoinSymbol(), transaction.getQuantity());
            } catch (CustomException e) {
                LogGUI.jTextArea.append(e.getMessage() + '\n');
            } finally {
                try {
                    TraderLibrary.updateProfileOfTrader(transaction.getWalletAddress(), transaction.getCoinSymbol(), transaction.getQuantity(), "SELL");
                } catch (Exception e) {
                    LogGUI.jTextArea.append(e.getMessage() + '\n');
                }
                LogGUI.jTextArea.append("Trader: " + traderName + " has sold " + transaction.getQuantity() + " quantity of coin: " + transaction.getCoinSymbol() + '\n');
            }
        }).start();
    }

}
