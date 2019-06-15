package com.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entites.Transaction;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    List<Transaction> findByAccountId(Long accountId);

}
