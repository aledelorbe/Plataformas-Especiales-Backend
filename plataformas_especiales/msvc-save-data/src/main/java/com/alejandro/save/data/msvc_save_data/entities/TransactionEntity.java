package com.alejandro.save.data.msvc_save_data.entities;

import com.alejandro.save.data.msvc_save_data.dtos.TransactionRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// In mysql the name of this table is 'transaction' but in this project
// the name of this class is 'Transaction'
@Entity
@Table(name = "transaction") 
public class TransactionEntity {
    
    // Mapping of class attributes with table fields in mysql
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;

    private String operation;
    
    private String amount;

    private String client;

    private String secret;

    private String reference;

    private String status;

    public TransactionEntity() {
    }

    // Factory method
    public static TransactionEntity createApproved(TransactionRequest request, String reference) {
        TransactionEntity entity = new TransactionEntity();
        entity.setOperation(request.getOperation());
        entity.setAmount(request.getAmount());
        entity.setClient(request.getClient());
        entity.setSecret(request.getSecret());
        entity.setStatus("Approved");
        entity.setReference(reference);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
