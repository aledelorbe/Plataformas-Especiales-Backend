package com.alejandro.save.data.msvc_save_data.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.save.data.msvc_save_data.dtos.TransactionRequest;
import com.alejandro.save.data.msvc_save_data.entities.TransactionEntity;


public interface TransactionService {
    
    // Declaration of methods to use in 'serviceImp' file

    // -----------------------------
    // Methods for address entity
    // -----------------------------

    List<TransactionEntity> findAll();

    Optional<TransactionEntity> findByReference(String reference);
    
    Optional<TransactionEntity> cancelTransaction(Long id, String reference, String status);

    TransactionEntity save(TransactionRequest transaction);

}
