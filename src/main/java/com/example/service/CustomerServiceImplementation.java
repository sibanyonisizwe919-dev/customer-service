package com.example.service;

import com.example.model.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

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

    private final ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        // Seed some sample data — runs once after the bean is constructed.
        store(new Customer("001", "Ada",   "Lovelace", "ada@example.com",   "+44 20 7946 0123"));
        store(new Customer("002", "Alan",  "Turing",   "alan@example.com",  "+44 20 7946 0456"));
        store(new Customer("003", "Grace", "Hopper",   "grace@example.com", "+1 203 555 0142"));
    }

    private void store(Customer customer) {
        customers.put(customer.uniqueCustomerId(), customer);
    }

    /** Look up a customer by their unique id; empty if not found. */
    public Optional<Customer> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(customers.get(id));
    }
}
