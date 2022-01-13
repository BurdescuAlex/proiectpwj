package com.example.proiect_java.Service;

import com.example.proiect_java.Model.Transaction;
import com.example.proiect_java.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    public Iterable<Transaction> getTransactions(){
        if(transactionRepository.count() !=0)
            return transactionRepository.findAll();
        return null;
    }
}
