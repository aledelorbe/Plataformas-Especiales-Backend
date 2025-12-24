package com.alejandro.msvc_processing_data.controllers;

import java.util.List;
import java.util.Optional;

import com.alejandro.msvc_processing_data.dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.msvc_processing_data.dto.TransactionResponseDto;
import com.alejandro.msvc_processing_data.services.TransactionService;
import com.alejandro.msvc_processing_data.utils.UtilValidation;

import jakarta.validation.Valid;

@RestController // To create an api rest.
@RequestMapping("/api/transactions") // To create a base path.
public class TransactionController {

    // To Inject the service dependency
    @Autowired
    private TransactionService service;

    @Autowired
    private UtilValidation utilValidation;

    // -----------------------------
    // Methods for transaction entity
    // -----------------------------

    // To create an endpoint that allows invoking the method findAll.
    @GetMapping()
    public List<TransactionResponseDto> transactions() {
        return service.findAll();
    }

    // To create an endpoint that allows invoking the method findByReference.
    @GetMapping("/by-reference/{reference}")
    public ResponseEntity<?> transactionByReference(@PathVariable String reference) {
        // Search for a specific transaction and if it's present then return it.
        Optional<TransactionResponseDto> optionalTransaction = service.findByReference(reference);

        if (optionalTransaction.isPresent()) {
            return ResponseEntity.ok(optionalTransaction.orElseThrow());
        }

        // Else returns code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows change the status of a certain transaction
    @PatchMapping("/cancel-Transaction/{id}/{reference}/{status}")
    public ResponseEntity<?> cancelTransaction(@PathVariable Long id, @PathVariable String reference, @PathVariable String status) {

        // Find specific client and if it's present then return specific client
        Optional<TransactionResponseDto> optionalTransaction = service.cancelTransaction(id, reference, status);

        if (optionalTransaction.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalTransaction.orElseThrow());
        }

        // Else return code response 404
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invoking the method save.
    // The annotation called 'RequestBody' allows receiving data of a transaction
    @PostMapping()
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto, BindingResult result) {
        // To handle the obligations of object attributes
        if (result.hasFieldErrors()) {
            return utilValidation.validation(result);
        }

        // When a new transaction is created to respond return the object of transactionDto
        TransactionResponseDto response = service.save(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

