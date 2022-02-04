package com.kdu.cryptotrading.dataFromExternalSourcesProcessor;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.Coin;
import com.kdu.cryptotrading.entities.CoinLibrary;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CoinCSVReader  {
    public static void readFromCSV(String pathToCSV) throws CustomException {
        CSVReader csvReader = null;
        try {
            FileReader file = new FileReader(pathToCSV);
            csvReader = new CSVReader(file);
            String[] nextRecord;
            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                try {
                    int rank = Integer.parseInt(nextRecord[1]);
                    String name = nextRecord[2];
                    String symbol = nextRecord[3];
                    double price = Double.parseDouble(nextRecord[4]);
                    double circulatingSupply = Double.parseDouble(nextRecord[5]);
                    Coin coin = new Coin(rank, name, symbol, price, circulatingSupply);
                    CoinLibrary.addCoin(coin);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new CustomException("A record doesn't contain enough fields to extract data");
                } catch (NumberFormatException ex) {
                    throw new CustomException("A field's data cannot be casted to an Integer");
                }
            }
        } catch (FileNotFoundException ex) {
            throw new CustomException("File was not found in the path provided! -> " + pathToCSV);
        } catch (IOException ex) {
            throw new CustomException("IO Exception encountered while reading from CSV!");
        } finally {
            try {
                if (csvReader == null) {
                    throw new CustomException("File was not found in the path provided! -> " + pathToCSV);
                }
                csvReader.close();
            } catch (IOException ex) {
                throw new CustomException("IO Exception encountered while trying to close the CSV Reader!");
            } catch (NullPointerException ex) {
                throw new CustomException("Null pointer exception encountered while trying to close the CSV Reader!");
            }
        }
    }
}
