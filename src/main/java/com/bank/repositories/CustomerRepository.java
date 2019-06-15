package com.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entites.Account;
import com.bank.entites.Customer;

@Repository

public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
    Iterable<Customer> findByCustomerId(Long customerId);

}
