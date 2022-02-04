package com.kdu.cryptotrading;

import com.kdu.cryptotrading.Comparators.CoinComparator;
import com.kdu.cryptotrading.Comparators.StrugglingTraderComparator;
import com.kdu.cryptotrading.Comparators.TopTraderComparator;
import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.Coin;
import com.kdu.cryptotrading.entities.Trader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class CryptoTradingApplicationTests {

    @Test
    void checkCoin() {
        Coin coin = new Coin(1, "coin1", "c1", 100, 1);
        assert (coin.getRank() == 1);
        assert (coin.getSymbol().equals("c1"));
        assert (coin.getPrice() == 100);
        assert (coin.getCirculatingSupply() == 1);
    }

    @Test
    void checkTrader() {
        Trader trader = new Trader("t1", "t1", "11", "0x");
        assert (trader.getFirstName().equals("t1"));
        assert (trader.getLastName().equals("t1"));
        assert (trader.getPhoneNumber().equals("11"));
        assert (trader.getWalletAddress().equals("0x"));
    }

    @Test
    void checkCoinComparator() {
        Coin coin1 = new Coin(1, "coin1", "c1", 100, 1);
        Coin coin2 = new Coin(2, "coin2", "c2", 200, 1);
        Coin coin3 = new Coin(3, "coin3", "c3", 300, 1);
        ArrayList<Coin> coins = new ArrayList<>();
        coins.add(coin1);
        coins.add(coin2);
        coins.add(coin3);
        coins.sort(new CoinComparator());
        assert (coins.get(0).getSymbol().equals("c3"));
        assert (coins.get(1).getSymbol().equals("c2"));
        assert (coins.get(2).getSymbol().equals("c1"));
    }

    @Test
    void checkTopTraderComparator() {
        Trader Trader1 = new Trader("t1", "t1", "11", "0x");
        Trader1.setProfit(100);
        Trader Trader2 = new Trader("t2", "t2", "11", "0x");
        Trader2.setProfit(200);
        Trader Trader3 = new Trader("t3", "t3", "11", "0x");
        Trader3.setProfit(300);
        ArrayList<Trader> traders = new ArrayList<>();
        traders.add(Trader1);
        traders.add(Trader2);
        traders.add(Trader3);
        traders.sort(new TopTraderComparator());
        assert (traders.get(0).getFirstName().equals("t3"));
        assert (traders.get(1).getFirstName().equals("t2"));
        assert (traders.get(2).getFirstName().equals("t1"));
    }

    @Test
    void checkStrugglingTraderComparator() {
        Trader Trader1 = new Trader("t1", "t1", "11", "0x");
        Trader1.setProfit(300);
        Trader Trader2 = new Trader("t2", "t2", "11", "0x");
        Trader2.setProfit(200);
        Trader Trader3 = new Trader("t3", "t3", "11", "0x");
        Trader3.setProfit(100);
        ArrayList<Trader> traders = new ArrayList<>();
        traders.add(Trader1);
        traders.add(Trader2);
        traders.add(Trader3);
        traders.sort(new StrugglingTraderComparator());
        assert (traders.get(0).getFirstName().equals("t3"));
        assert (traders.get(1).getFirstName().equals("t2"));
        assert (traders.get(2).getFirstName().equals("t1"));
    }

    @Test
    void checkCustomError() {
        CustomException customException = new CustomException("test");
        assert (customException.getMessage().equals("test"));
    }


}
