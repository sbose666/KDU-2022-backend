package com.kdu.cryptotrading.entities;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class Trader {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String walletAddress;
    private double profit;
    private Map<String, ArrayList<ArrayList<Double>>> cryptoProfile;

    public Trader(String firstName, String lastName, String phoneNumber, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.walletAddress = walletAddress;
        this.profit = 0.0;
        this.cryptoProfile = Collections.synchronizedMap(new HashMap<>());
    }

    public void addCoin(String coinSymbol, double quantity) throws CustomException {
        double curPrice = CoinLibrary.getCoin(coinSymbol).getPrice();
        if (cryptoProfile.containsKey(coinSymbol)) {
            boolean done = false;
            for (ArrayList<Double> priceAndQuantity : cryptoProfile.get(coinSymbol)) {
                if (priceAndQuantity.get(0) == curPrice) {
                    priceAndQuantity.set(1, priceAndQuantity.get(1) + quantity);
                    done = true;
                    break;
                }
            }
            if (!done) {
                ArrayList<Double> priceAndQuantity = new ArrayList<>();
                priceAndQuantity.add(curPrice);
                priceAndQuantity.add(quantity);
                cryptoProfile.get(coinSymbol).add(priceAndQuantity);
            }
        } else {
            ArrayList<Double> cur = new ArrayList<>();
            cur.add(curPrice);
            cur.add(quantity);
            cryptoProfile.put(coinSymbol, new ArrayList<>());
            cryptoProfile.get(coinSymbol).add(cur);
        }
    }

    public void removeCoin(String coinSymbol, double quantity) throws CustomException {
        double curPrice = CoinLibrary.getCoin(coinSymbol).getPrice();
        if (cryptoProfile.containsKey(coinSymbol)) {
            for (ArrayList<Double> priceAndQuantity : cryptoProfile.get(coinSymbol)) {
                if (priceAndQuantity.get(1) > quantity) {
                    priceAndQuantity.set(1, priceAndQuantity.get(1) - quantity);
                    profit += quantity * (curPrice - priceAndQuantity.get(0));
                    break;
                } else {
                    quantity -= priceAndQuantity.get(1);
                    profit += priceAndQuantity.get(1) * (curPrice - priceAndQuantity.get(0));
                    cryptoProfile.get(coinSymbol).remove(priceAndQuantity);
                    if (quantity == 0) {
                        break;
                    }
                }
            }
            if (cryptoProfile.get(coinSymbol).size() == 0) {
                cryptoProfile.remove(coinSymbol);
            }
        } else {
            throw new CustomException("No coin: " + coinSymbol + "in trader: " + firstName + " " + lastName + "'s profile");
        }
    }

    public boolean traderHasCoin(String coinSymbol) {
        return cryptoProfile.containsKey(coinSymbol);
    }

    public double getCoinQuantityInCryptoProfile(String coinSymbol) {
        return cryptoProfile.get(coinSymbol).stream().mapToDouble(x -> x.get(1)).sum();
    }

    public double getProfit() {
        for (String coinSymbol : cryptoProfile.keySet()) {
            for (ArrayList<Double> priceAndQuantity : cryptoProfile.get(coinSymbol)) {
                try {
                    profit += priceAndQuantity.get(1) * (CoinLibrary.getCoin(coinSymbol).getPrice() - priceAndQuantity.get(0));
                } catch (CustomException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return profit;
    }
}
