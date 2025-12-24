package com.alejandro.msvc_processing_data.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.msvc_processing_data.dto.TransactionResponseDto;
import com.alejandro.msvc_processing_data.dto.TransactionRequestDto;

public interface TransactionService {
    
    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for address entity
    // -----------------------------

    List<TransactionResponseDto> findAll();

    Optional<TransactionResponseDto> findByReference(String reference);

    Optional<TransactionResponseDto> cancelTransaction(Long id, String reference, String status);

    TransactionResponseDto save(TransactionRequestDto transactionRequestDto);
}
