package com.alejandro.msvc_processing_data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TransactionRequestDto {
    
    // to disallow numeric characters
    @Pattern(regexp = "^[^0-9]+$", message = "{Pattern.transaction.operation}")
    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String operation;
    
    // to allow only numeric characters and one character: .
    @NotBlank // To obligate to this attribute not to empty or blank values.
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "{Pattern.transaction.amount}")
    private String amount;

    // to disallow numeric characters
    @Pattern(regexp = "^[^0-9]+$", message = "{Pattern.transaction.client}")
    @NotBlank // To obligate to this attribute not to empty or blank values.
    @Size(max = 30)
    private String client;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String secret;

    public TransactionRequestDto() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
