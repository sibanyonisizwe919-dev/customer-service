package com.example.service;

import com.example.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(Long id);

    Optional<Customer> saveCustomer(Customer customer);

    Optional<List<Customer>> getCustomers();

}
