package com.kdu.cryptotrading.gui;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.Coin;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.kdu.cryptotrading.entities.Trader;
import com.kdu.cryptotrading.entities.TraderLibrary;

import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void createMenu() {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("1. Display a coin's details");
                System.out.println("2. Display top coins in the market");
                System.out.println("3. Display a trader's portfolio");
                System.out.println("4. Display the net profit or loss of a trader");
                System.out.println("5. Display top traders");
                System.out.println("6. Display struggling traders");
                System.out.println("7. Exit");
                System.out.println("Please enter an option: ");
                String input = sc.nextLine();
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        System.out.println("Enter coin's name or symbol");
                        String query = sc.nextLine();
                        try {
                            System.out.println(CoinLibrary.getCoin(query));
                        } catch (CustomException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Enter the number of top coins you would like to see");
                        input = sc.nextLine();
                        int bound = Integer.parseInt(input);
                        try {
                            List<Coin> topCoinsList = CoinLibrary.getTopCoins(bound);
                            System.out.println("The top " + bound + " coins are: ");
                            for (Coin coin : topCoinsList)
                                System.out.println(coin);
                        } catch (CustomException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter trader's first name");
                        String fName = sc.nextLine();
                        System.out.println("Enter trader's second name");
                        String lName = sc.nextLine();
                        try {
                            System.out.println(TraderLibrary.getTrader(fName, lName));
                        } catch (CustomException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Enter trader's first name");
                        fName = sc.nextLine();
                        System.out.println("Enter trader's second name");
                        lName = sc.nextLine();
                        try {
                            System.out.println(TraderLibrary.getTrader(fName, lName).getProfit());
                        } catch (CustomException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Enter the number of top traders you would like to see");
                        input = sc.nextLine();
                        bound = Integer.parseInt(input);
                        try {
                            List<Trader> topTradersList = TraderLibrary.getTopTraders(bound);
                            System.out.println("The top " + bound + " traders are: ");
                            for (Trader trader : topTradersList)
                                System.out.println(trader);
                        } catch (CustomException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Enter the number of struggling traders you would like to see");
                        input = sc.nextLine();
                        bound = Integer.parseInt(input);
                        List<Trader> strugglingTradersList = TraderLibrary.getStrugglingTraders(bound);
                        System.out.println("The top " + bound + " struggling traders are: ");
                        for (Trader trader : strugglingTradersList)
                            System.out.println(trader);
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                System.out.println("Do you want to continue? (y/n)");
                input = sc.nextLine();
                if (input.equals("n"))
                    System.exit(0);
            }
        }).start();
    }
}
