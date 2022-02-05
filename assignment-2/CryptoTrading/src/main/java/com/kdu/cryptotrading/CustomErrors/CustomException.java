package com.kdu.cryptotrading.CustomErrors;

public class CustomException extends Exception {
    /**
     * Constructs a new exception with custom string as its detail message.
     *
     * @param errorMessage custom string
     */
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
