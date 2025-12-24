package com.alejandro.save.data.msvc_save_data.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.save.data.msvc_save_data.dtos.TransactionRequest;
import com.alejandro.save.data.msvc_save_data.entities.TransactionEntity;
import com.alejandro.save.data.msvc_save_data.services.TransactionService;


@RestController // To create an api rest.
@RequestMapping("/api/transactions") // To create a base path.
public class TransactionController {

    // To Inject the service dependency
    @Autowired
    private TransactionService service;

    // -----------------------------
    // Methods for transaction entity
    // -----------------------------

    // To create an endpoint that allows invoking the method findAll.
    @GetMapping("/get-all-transactions")
    public List<TransactionEntity> transactions() {
        return service.findAll();
    }

    // To create an endpoint that allows invoking the method findByReference.
    @GetMapping("/by-reference/{reference}")
    public ResponseEntity<?> transactionByReference(@PathVariable String reference) {
        // Search for a specific transaction
        Optional<TransactionEntity> optionalTransaction = service.findByReference(reference);

        if (optionalTransaction.isPresent()) {
            return ResponseEntity.ok(optionalTransaction.orElseThrow());
        }

        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows change the status of a certain transaction
    @PutMapping("/cancel-Transaction/{id}/{reference}/{status}")
    public ResponseEntity<?> cancelTransaction(@PathVariable Long id, @PathVariable String reference, @PathVariable String status) {

        // Find specific client and if it's present then return specific client
        Optional<TransactionEntity> optionalTransaction = service.cancelTransaction(id, reference, status);

        if (optionalTransaction.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalTransaction.orElseThrow());
        }

        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invoking the method save.
    // The annotation called 'RequestBody' allows receiving data of a transaction
    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequest transaction) {

        // When a new transaction is created to respond return the same transaction
        TransactionEntity response = service.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
