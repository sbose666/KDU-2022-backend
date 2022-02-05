package com.kdu.cryptotrading.entities;

import com.kdu.cryptotrading.Comparators.StrugglingTraderComparator;
import com.kdu.cryptotrading.Comparators.TopTraderComparator;
import com.kdu.cryptotrading.CustomErrors.CustomException;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TraderLibrary class contains all the traders in the market.
 */
@Data
public class TraderLibrary {
    /**
     * Hashmap of all traders in the market, with (key, value) as (traderWalletAddress, trader).
     */
    private static HashMap<String, Trader> traders = new HashMap<>();

    /**
     * Synchronized map of all traders in the market, with (key, value) as (traderWalletAddress, trader).
     * Thread safe.
     */
    private static Map<String, Trader> synchronizedMapOfTraders = null;

    /**
     * Get the trader with the given wallet address.
     *
     * @param walletAddress wallet address of the trader.
     * @return trader with the given wallet address.
     */
    public static Trader getTrader(String walletAddress) {
        return synchronizedMapOfTraders.get(walletAddress);
    }

    /**
     * Get the trader with the given first name and second name.
     *
     * @param firstName first name of the trader.
     * @param lastName  last name of the trader.
     * @return trader with the given first name and second name.
     * @throws CustomException if no trader with the given first name and second name exists.
     */
    public static Trader getTrader(String firstName, String lastName) throws CustomException {
        Trader result = null;

        for (Map.Entry<String, Trader> entry : synchronizedMapOfTraders.entrySet()) {
            Trader trader = entry.getValue();
            if (trader.getFirstName().equalsIgnoreCase(firstName) && trader.getLastName().equalsIgnoreCase(lastName)) {
                result = trader;
                break;
            }
        }
        if (result == null) {
            throw new CustomException("No such trader found");
        }
        return result;
    }

    /**
     * Add a trader to the trader library.
     *
     * @param trader trader to be added.
     */
    public static void addTrader(Trader trader) {
        traders.put(trader.getWalletAddress(), trader);
    }

    /**
     * Synchronize the trader library.
     */
    public static void synchronizeMembers() {
        synchronizedMapOfTraders = Collections.synchronizedMap(traders);
    }

    /**
     * Method to update a trader's profile in the market.
     *
     * @param walletAddress wallet address of the trader.
     * @param coinSymbol    coin symbol of the coin.
     * @param quantity      quantity of the coin.
     * @param type          type of transaction.
     * @throws CustomException if the transaction is invalid.
     */
    public static void updateProfileOfTrader(String walletAddress, String coinSymbol, double quantity, String type) throws CustomException {
        switch (type) {
            case "BUY":
                synchronizedMapOfTraders.get(walletAddress).addCoin(coinSymbol, quantity);
                break;
            case "SELL":
                synchronizedMapOfTraders.get(walletAddress).removeCoin(coinSymbol, quantity);
                break;
        }
    }

    /**
     * Get the top traders in the market.
     *
     * @param bound Number of top traders to be returned.
     * @return List of top traders.
     * @throws CustomException if the bound is greater than the number of traders in the market.
     */
    public static List<Trader> getTopTraders(int bound) throws CustomException {
        if (synchronizedMapOfTraders.size() < bound) {
            throw new CustomException("Not enough traders in the market");
        }
        ArrayList<Trader> traderArrayList = new ArrayList<>();
        synchronizedMapOfTraders.forEach((k, v) -> traderArrayList.add(new Trader(v)));
        return traderArrayList
                .stream()
                .sorted(new TopTraderComparator())
                .limit(bound).collect(Collectors.toUnmodifiableList());
    }

    /**
     * Get the struggling traders in the market.
     *
     * @param bound Number of struggling traders to be returned.
     * @return List of struggling traders.
     * @throws CustomException if the bound is greater than the number of traders in the market.
     */
    public static List<Trader> getStrugglingTraders(int bound) throws CustomException {
        if (synchronizedMapOfTraders.size() < bound) {
            throw new CustomException("Not enough traders in the market");
        }
        ArrayList<Trader> traderArrayList = new ArrayList<>();
        synchronizedMapOfTraders.forEach((k, v) -> traderArrayList.add(new Trader(v)));
        return traderArrayList
                .stream()
                .sorted(new StrugglingTraderComparator())
                .limit(bound).collect(Collectors.toUnmodifiableList());
    }
}
