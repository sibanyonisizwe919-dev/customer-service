package com.example.service;

import com.example.model.Customer;
import com.example.persistance.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Application-scoped service — the "behavior" half of the Model layer.
 *
 * @ApplicationScoped means a single instance is shared across all requests
 * (a singleton in CDI terms). @Inject it into the controller rather than
 * instantiating it directly — that's what makes this testable and decouples
 * the REST layer from where the data actually lives.
 *
 * The in-memory map here is a stand-in. Add a repository package and replace
 * the body of findById with a JPA call when you wire up persistence.
 */
@ApplicationScoped
public class CustomerServiceImplementation implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public Optional<Customer> saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return Optional.ofNullable(savedCustomer);
    }

    /** Look up a customer by their unique id; empty if not found. */
    public Optional<Customer> findById(Long id) {
        Customer customer = customerRepository.findById(id);
        if(customer==null){
            return Optional.empty();
        }
        return Optional.of(customer);
    }

    public Optional<List<Customer>> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(customers);
    }


}
