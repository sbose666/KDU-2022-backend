package com.kdu.cryptotrading;


import com.kdu.cryptotrading.dataFromExternalSourcesProcessor.CoinCSVReader;
import com.kdu.cryptotrading.dataFromExternalSourcesProcessor.TraderCSVReader;
import com.kdu.cryptotrading.dataFromExternalSourcesProcessor.TransactionJSONReader;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.kdu.cryptotrading.entities.TraderLibrary;
import com.kdu.cryptotrading.gui.Menu;
import com.kdu.cryptotrading.gui.LogGUI;


public class CryptoTradingApplication {
    public static void main(String[] args) throws Exception {
        LogGUI.initialize();
        CoinCSVReader.readFromCSV(".\\src\\main\\resources\\coins.csv");
        CoinLibrary.synchronizeMembers();
        TraderCSVReader.readFromCSV(".\\src\\main\\resources\\traders.csv");
        TraderLibrary.synchronizeMembers();
        Menu.createMenu();
        TransactionJSONReader.readFromJSON(".\\src\\main\\resources\\small_transaction.json");
    }
}