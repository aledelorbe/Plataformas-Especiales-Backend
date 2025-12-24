package com.alejandro.save.data.msvc_save_data.dtos;


public class TransactionRequest {
    
    private String operation;
    
    private String amount;

    private String client;

    private String secret;

    public String getOperation() {
        return operation;
    }

    public String getAmount() {
        return amount;
    }

    public String getClient() {
        return client;
    }

    public String getSecret() {
        return secret;
    }

}
