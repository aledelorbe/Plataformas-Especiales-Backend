package com.alejandro.save.data.msvc_save_data.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.save.data.msvc_save_data.dtos.TransactionRequest;
import com.alejandro.save.data.msvc_save_data.entities.TransactionEntity;
import com.alejandro.save.data.msvc_save_data.repositories.TransactionRepository;

import jakarta.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImp implements TransactionService {

    // To inject the repository dependency.
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private EntityManager entityManager;

    // -----------------------------
    // Methods for transaction entity
    // -----------------------------

    // To list all transactions (records) in the table 'transactions'
    @Override
    @Transactional(readOnly = true)
    public List<TransactionEntity> findAll() {
        return (List<TransactionEntity>) repository.findAll(); // cast because the method findAll returns an iterable.
    }

    // To get a specific transaction based on its reference
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionEntity> findByReference(String reference) {
        return Optional.ofNullable(repository.getTransactionByReference(reference));
    }

    // To cancel the transaction
    @Override
    @Transactional
    public Optional<TransactionEntity> cancelTransaction(Long id, String reference, String status) {

        // Find a specific transaction
        Optional<TransactionEntity> optionalTransaction = repository.findById(id);

        // If the transaction is present then...
        if (optionalTransaction.isPresent()) {
            int rowsUpdated = repository.updateStatusByIdAndReference(id, status);

            if (rowsUpdated > 0) {
                Optional<TransactionEntity> updated = repository.findById(id);
                updated.ifPresent(entityManager::refresh);
                return updated;
            }

            return Optional.empty();
        }

        return optionalTransaction;
    }

    // To save a new transaction in the db
    @Override
    @Transactional
    public TransactionEntity save(TransactionRequest transaction) {
        return repository.save(TransactionEntity.createApproved(transaction, this.generateReference()));
    }

    private String generateReference() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Six numbers
        for (int i = 0; i < 6; i++) {
            sb.append(rand.nextInt(10));
        }

        return sb.toString();
    }
}