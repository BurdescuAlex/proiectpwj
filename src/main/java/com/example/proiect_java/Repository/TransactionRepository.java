package com.example.proiect_java.Repository;


import com.example.proiect_java.Model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
}
