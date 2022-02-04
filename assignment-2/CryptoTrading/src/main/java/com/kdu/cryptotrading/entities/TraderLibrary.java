package com.kdu.cryptotrading.entities;

import com.kdu.cryptotrading.Comparators.StrugglingTraderComparator;
import com.kdu.cryptotrading.Comparators.TopTraderComparator;
import com.kdu.cryptotrading.CustomErrors.CustomException;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class TraderLibrary {
    private static HashMap<String, Trader> traders = new HashMap<>();
    private static Map<String, Trader> synchronizedMapOfTraders = null;

    public static Trader getTrader(String walletAddress) {
        return synchronizedMapOfTraders.get(walletAddress);
    }

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

    public static void addTrader(Trader trader) {
        traders.put(trader.getWalletAddress(), trader);
    }

    public static void synchronizeMembers() {
        synchronizedMapOfTraders = Collections.synchronizedMap(traders);
    }

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

    public static List<Trader> getTopTraders(int bound) throws CustomException {
        if (synchronizedMapOfTraders.size() < bound) {
            throw new CustomException("Not enough traders in the market");
        }
        ArrayList<Trader> traderArrayList = new ArrayList<>();
        synchronizedMapOfTraders.forEach((k, v) -> {
            traderArrayList.add(v);
        });
        List<Trader> result = Collections
                .unmodifiableList(
                        traderArrayList
                                .stream()
                                .sorted(new TopTraderComparator())
                                .limit(bound)
                                .collect(Collectors.toList())
                );
        return result;
    }

    public static List<Trader> getStrugglingTraders(int bound) {
        ArrayList<Trader> traderArrayList = new ArrayList<>();
        synchronizedMapOfTraders.forEach((k, v) -> {
            traderArrayList.add(v);
        });
        List<Trader> result = Collections
                .unmodifiableList(
                        traderArrayList
                                .stream()
                                .sorted(new StrugglingTraderComparator())
                                .limit(bound)
                                .collect(Collectors.toList())
                );
        return result;
    }
}
