package com.example.service;

import com.example.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(String id);
}
