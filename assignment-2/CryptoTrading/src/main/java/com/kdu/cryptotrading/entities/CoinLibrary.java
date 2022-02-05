package com.kdu.cryptotrading.entities;

import com.kdu.cryptotrading.Comparators.CoinComparator;
import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.gui.LogGUI;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used to store all the coins in the market and their information.
 */
@Data
public class CoinLibrary {
    /**
     * HashMap that stores all the coins in the market.
     */
    private static HashMap<String, Coin> coins = new HashMap<>();

    /**
     * Synchronized map that stores the coins in the market.
     * Thread safe.
     */
    private static Map<String, Coin> synchronizedMapOfCoins = null;

    /**
     * This method is used to add a new coin to the library.
     *
     * @param coin The coin to be added.
     * @throws CustomException If the coin already exists in the library.
     */
    public static void addCoin(Coin coin) throws CustomException {
        if (coins.containsKey(coin.getSymbol())) {
            throw new CustomException("Duplicate Coin");
        }
        coins.put(coin.getSymbol(), coin);
    }

    /**
     * This method is used to update the coins map to a synchronized map.
     */
    public static void synchronizeMembers() {
        synchronizedMapOfCoins = Collections.synchronizedMap(coins);
    }

    /**
     * This method is used to get the coin with the given symbol.
     *
     * @param coin The coin symbol.
     * @return The coin with the given symbol.
     * @throws CustomException If the coin is not found.
     */
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

    /**
     * This method is used to get the top coins in the market according to the price.
     *
     * @param bound The number of coins to be returned.
     * @return The top coins in the market according to the price.
     * @throws CustomException If the bound is greater than the number of coins in the market.
     */
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

    /**
     * This method is used to get the current supply of a given coin in the market.
     *
     * @param coinSymbol The coin symbol.
     * @return The current supply of the coin.
     */
    public static double getCurrentCoinSupply(String coinSymbol) {
        return synchronizedMapOfCoins.get(coinSymbol).getCirculatingSupply();
    }

    /**
     * This method is used to get the current price of a given coin in the market.
     *
     * @param coinSymbol The coin symbol.
     * @return The current price of the coin.
     */
    public static double getCurrentCoinPrice(String coinSymbol) {
        return synchronizedMapOfCoins.get(coinSymbol).getPrice();
    }

    /**
     * This method is used to decrease the supply of a coin by the given amount.
     *
     * @param coinSymbol The coin symbol.
     * @param volume     The amount to be decreased.
     * @throws CustomException If the volume is greater than the current supply.
     */
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

    /**
     * This method is used to increase the supply of a coin by the given amount.
     *
     * @param coinSymbol The coin symbol.
     * @param volume     The amount to be increased.
     * @throws CustomException If the volume is less than 0.
     */
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

    /**
     * This method is used to get the update the price of a given coin in the market.
     *
     * @param coinSymbol The coin symbol.
     * @param price      The new price of the coin.
     * @throws CustomException If the price is less than 0.
     */
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
