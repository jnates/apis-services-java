package com.vobi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vobi.bank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
