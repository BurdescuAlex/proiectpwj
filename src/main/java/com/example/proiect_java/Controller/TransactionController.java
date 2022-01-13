package com.example.proiect_java.Controller;

import com.example.proiect_java.Model.Transaction;
import com.example.proiect_java.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history")
    public ResponseEntity<Iterable<Transaction>> getHisotry()
    {
        if(transactionService.getTransactions() != null)
            return ResponseEntity.ok(transactionService.getTransactions());
        return ResponseEntity.noContent().build();
    }
}
