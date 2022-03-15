package com.zube.service;

import com.zube.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Integer amount, String reference){

        LocalDateTime timestamp = LocalDateTime.now();

        Transaction transaction = new Transaction(amount, reference, timestamp);
        transactions.add(transaction);

        return transaction;
    }
}
