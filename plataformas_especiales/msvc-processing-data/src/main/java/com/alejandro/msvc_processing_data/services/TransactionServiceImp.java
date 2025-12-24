package com.alejandro.msvc_processing_data.services;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.alejandro.msvc_processing_data.dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.msvc_processing_data.clients.MsvcSaveDataClientRest;
import com.alejandro.msvc_processing_data.dto.TransactionResponseDto;

import feign.FeignException;


@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private MsvcSaveDataClientRest client; 

    // -----------------------------
    // Methods for transaction entity
    // -----------------------------

    // To list all clients (records) in the table 'clients'
    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponseDto> findAll() {
        return client.allTransactions();
    }

    // To get a specific client based on its reference
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionResponseDto> findByReference(String reference) {
        try {
            return Optional.of(client.transactionByReference(reference));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        } catch (FeignException e) {
            throw new RuntimeException("Error communicating with save-data service", e);
        }
    }

    // To cancel a certain transaction
    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionResponseDto> cancelTransaction(Long id, String reference, String status) {
        try {
            return Optional.of(client.cancelTransaction(id, reference, status));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        } catch (FeignException e) {
            throw new RuntimeException("Error communicating with save-data service", e);
        }
    }

    // To save a new transaction in the db
    @Override
    @Transactional
    public TransactionResponseDto save(TransactionRequestDto transactionRequestDto) {

        try {
            transactionRequestDto.setSecret(this.decrypt(transactionRequestDto.getSecret()));
        } catch (Exception e) {
            throw new RuntimeException("Error when decrypt the secret", e);
        }

        return client.saveTransaction(transactionRequestDto);
    }

    private String decrypt(String encryptedBase64) throws Exception {

        String KEY = "12345678901234567890123456789012";
        String IV = "1234567890123456";

        byte[] keyBytes = KEY.getBytes("UTF-8");
        byte[] ivBytes = IV.getBytes("UTF-8");
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encryptedBytes);

        return new String(decrypted, "UTF-8");
    }
}
