package com.kdu.cryptotrading.dataFromExternalSourcesProcessor;

import com.kdu.cryptotrading.CustomErrors.CustomException;
import com.kdu.cryptotrading.entities.transactions.Transaction;
import com.kdu.cryptotrading.entities.transactions.TransactionFactory;
import com.kdu.cryptotrading.entities.transactions.processor.TransactionProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class TransactionJSONReader {
    public static void readFromJSON(String pathToJSON) throws Exception {
        try {
            FileReader file = new FileReader(pathToJSON);
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(file);
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                ArrayList<String> transaction = new ArrayList<>();
                transaction.add((String) jsonObject.get("type"));
                JSONObject transactionData = (JSONObject) jsonObject.get("data");
                for (Object obj : transactionData.values()) {
                    transaction.add(1, String.valueOf(obj));
                }
                Transaction newTransaction = TransactionFactory.createTransaction(transaction);
                TransactionProcessor.processTransaction(newTransaction);
            }
        } catch (FileNotFoundException ex) {
            throw new CustomException("File was not found in the path provided! -> " + pathToJSON);
        }
    }
}