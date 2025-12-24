package com.alejandro.save.data.msvc_save_data.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.save.data.msvc_save_data.entities.TransactionEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
 
    // ---------------------
    // Custom queries ------
    // ---------------------

    // To get a specific transaction based on its reference value
    @Query("SELECT t FROM TransactionEntity t WHERE t.reference = ?1")
    TransactionEntity getTransactionByReference(String reference);

    // To change the status to cancel
    @Modifying
    @Transactional
    @Query("UPDATE TransactionEntity t SET t.status = :status WHERE t.id = :id")
    int updateStatusByIdAndReference(@Param("id") Long id, @Param("status") String status);

}

