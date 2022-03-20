package com.zube.web;

import com.zube.dto.TransactionDto;
import com.zube.model.Transaction;
import com.zube.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return transactionService.getTransactions();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody TransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto.getAmount(), transactionDto.getReference());
    }

}
