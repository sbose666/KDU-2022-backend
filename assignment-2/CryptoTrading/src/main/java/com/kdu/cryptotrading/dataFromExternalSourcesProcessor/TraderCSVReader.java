package com.kdu.cryptotrading.dataFromExternalSourcesProcessor;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.Trader;
import com.kdu.cryptotrading.entities.TraderLibrary;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TraderCSVReader {
    public static void readFromCSV(String pathToCSV) throws CustomException {
        /**
         * Reads the CSV file and creates a TraderLibrary object
         */
        CSVReader csvReader = null;
        try {
            FileReader file = new FileReader(pathToCSV);
            csvReader = new CSVReader(file);
            String[] nextRecord;
            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                try {
                    String firstName = nextRecord[1];
                    String lastName = nextRecord[2];
                    String phoneNumber = nextRecord[3];
                    String walletAddress = nextRecord[4];
                    Trader trader = new Trader(firstName, lastName, phoneNumber, walletAddress);
                    TraderLibrary.addTrader(trader);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new CustomException("A record doesn't contain enough fields to extract data");
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
