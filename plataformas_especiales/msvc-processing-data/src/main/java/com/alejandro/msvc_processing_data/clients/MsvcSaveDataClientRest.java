package com.alejandro.msvc_processing_data.clients;

import java.util.List;

import com.alejandro.msvc_processing_data.dto.TransactionRequestDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alejandro.msvc_processing_data.dto.TransactionResponseDto;

@FeignClient(name = "msvc-save-data", url = "${external.service}")
public interface MsvcSaveDataClientRest {

    @GetMapping("/api/transactions/get-all-transactions")
    List<TransactionResponseDto> allTransactions();

    @GetMapping("/api/transactions/by-reference/{reference}")
    TransactionResponseDto transactionByReference(@PathVariable String reference);

    @PutMapping("api/transactions/cancel-Transaction/{id}/{reference}/{status}")
    TransactionResponseDto cancelTransaction(@PathVariable Long id, @PathVariable String reference, @PathVariable String status);
    
    @PostMapping("/api/transactions/save")
    TransactionResponseDto saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto);

}
