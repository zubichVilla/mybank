package com.zube.service;

import com.zube.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private final String bankSlogan;

    List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}") String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction createTransaction(Integer amount, String reference){

        LocalDateTime timestamp = LocalDateTime.now();

        Transaction transaction = new Transaction(amount, reference, timestamp, bankSlogan);
        transactions.add(transaction);

        return transaction;
    }
}
