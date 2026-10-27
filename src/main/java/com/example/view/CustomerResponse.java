package com.example.view;

import com.example.model.Customer;

/**
 * View model for a successful customer lookup.
 *
 * In a REST API the "view" is the JSON representation sent back to the
 * client. Wrapping the Customer in a dedicated record keeps the response
 * shape explicit ({"customer": {...}}) and decouples the contract from the
 * domain model, so you can change the entity later without breaking clients.
 */
public record CustomerResponse(Customer customer) {
}
