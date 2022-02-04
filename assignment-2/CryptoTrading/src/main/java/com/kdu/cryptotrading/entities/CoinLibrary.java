package com.kdu.cryptotrading.entities;

import com.kdu.cryptotrading.Comparators.CoinComparator;
import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.gui.LogGUI;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class CoinLibrary {
    private static HashMap<String, Coin> coins = new HashMap<>();

    private static Map<String, Coin> synchronizedMapOfCoins = null;


    public static void addCoin(Coin coin) throws CustomException {
        if (coins.containsKey(coin.getSymbol())) {
            throw new CustomException("Duplicate Coin");
        }
        coins.put(coin.getSymbol(), coin);
    }

    public static void synchronizeMembers() {
        synchronizedMapOfCoins = Collections.synchronizedMap(coins);
    }

    public static Coin getCoin(String coin) throws CustomException {
        Coin result = null;
        for (Map.Entry<String, Coin> entry : synchronizedMapOfCoins.entrySet()) {
            Coin value = entry.getValue();
            if (coin.equalsIgnoreCase(value.getSymbol()) || coin.equalsIgnoreCase(value.getName())) {
                result = value;
                break;
            }
        }
        if (result == null) {
            throw new CustomException("No coin with symbol/name: " + coin + " found");
        }
        return result;
    }

    public static List<Coin> getTopCoins(int bound) throws CustomException {
        if (synchronizedMapOfCoins.size() < bound) {
            throw new CustomException("Not enough coins in the market");
        }
        ArrayList<Coin> coinArrayList = new ArrayList<>();
        synchronizedMapOfCoins.forEach((k, v) -> {
            coinArrayList.add(v);
        });
        List<Coin> result = Collections
                .unmodifiableList(
                        coinArrayList
                                .stream()
                                .sorted(new CoinComparator())
                                .limit(bound)
                                .collect(Collectors.toList())
                );
        return result;
    }

    public static double getCurrentCoinSupply(String coinSymbol) {
        return synchronizedMapOfCoins.get(coinSymbol).getCirculatingSupply();
    }

    public static double getCurrentCoinPrice(String coinSymbol) {
        return synchronizedMapOfCoins.get(coinSymbol).getPrice();
    }

    public static void decreaseVolume(String coinSymbol, double volume) throws CustomException {
        if (!synchronizedMapOfCoins.containsKey(coinSymbol)) {
            throw new CustomException("Coin with symbol " + coinSymbol + " not found");
        }
        Coin updatedCoin = synchronizedMapOfCoins.get(coinSymbol);
        double currentSupply = updatedCoin.getCirculatingSupply();
        updatedCoin.setCirculatingSupply(currentSupply - volume);
        synchronizedMapOfCoins.put(coinSymbol, updatedCoin);
        LogGUI.jTextArea.append("The circulating supply of coin: " + updatedCoin.getSymbol() + " has been decreased from " + currentSupply + " to " + updatedCoin.getCirculatingSupply() + '\n');
    }

    public static void addVolume(String coinSymbol, double volume) throws CustomException {
        if (!synchronizedMapOfCoins.containsKey(coinSymbol)) {
            throw new CustomException("Coin with symbol " + coinSymbol + " not found");
        }
        Coin updatedCoin = synchronizedMapOfCoins.get(coinSymbol);
        double currentSupply = updatedCoin.getCirculatingSupply();
        updatedCoin.setCirculatingSupply(currentSupply + volume);
        synchronizedMapOfCoins.put(coinSymbol, updatedCoin);
        LogGUI.jTextArea.append("The circulating supply of coin: " + updatedCoin.getName() + " has been increased from " + currentSupply + " to " + updatedCoin.getCirculatingSupply() + "\n");
    }

    public static void updatePrice(String coinSymbol, double price) throws CustomException {
        if (!synchronizedMapOfCoins.containsKey(coinSymbol)) {
            throw new CustomException("Coin with symbol " + coinSymbol + " not found");
        }
        Coin updatedCoin = synchronizedMapOfCoins.get(coinSymbol);
        double currentPrice = updatedCoin.getPrice();
        updatedCoin.setPrice(price);
        synchronizedMapOfCoins.put(coinSymbol, updatedCoin);

        LogGUI.jTextArea.append("The price of coin: " + updatedCoin.getName() + " has been changed from " + currentPrice + " to " + updatedCoin.getPrice() + "\n");
    }
}
