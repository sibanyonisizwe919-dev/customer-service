package com.example.model;

/**
 * Immutable customer DTO — the "data" half of the Model layer.
 *
 * A record gives you the canonical constructor, accessors (customer.email()),
 * equals/hashCode/toString for free. JSON-B serializes the components to JSON
 * automatically — e.g. {"uniqueCustomerId":"CUST-001", ...}.
 *
 * If you later add JPA, swap this for a class with a no-arg constructor and
 * getters/setters; records work as DTOs but aren't natural JPA entities.
 */
public record Customer(
        String uniqueCustomerId,
        String name,
        String surname,
        String email,
        String contact
) {
}
