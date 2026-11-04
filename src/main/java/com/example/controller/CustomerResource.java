package com.example.controller;

import com.example.model.Customer;
import com.example.service.CustomerServiceImplementation;
import com.example.view.CustomerResponse;
import com.example.view.ErrorResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST controller for customer operations.
 *
 * Base path /api/customer-details comes from MyEEApplication's @ApplicationPath("/api").
 *
 * Thin by design — parse the request, delegate to the model (CustomerService),
 * return a view (CustomerResponse / ErrorResponse). No business logic here.
 */
@Path("/customer-details")
public class CustomerResource {

    // Field injection — CDI resolves the @ApplicationScoped CustomerServiceImplementation.
    @Inject
    private CustomerServiceImplementation customerService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Long id) {
        return customerService.findById(id)
                .map(customer -> Response.ok(new CustomerResponse(customer)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Customer not found: " + id))
                        .build());
    }

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        // getCustomers() returns Optional<List<Customer>> — empty means none found.
        return customerService.getCustomers()
                .map(customers -> Response.ok(customers).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("No customers found"))
                        .build());
    }

    @PUT
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCustomer(Customer customer) {
        // No @RequestBody — JAX-RS unmarshals the JSON body into the entity
        // parameter automatically because @Consumes is declared above.
        return customerService.saveCustomer(customer)
                .map(saved -> Response.status(Response.Status.CREATED)
                        .entity(new CustomerResponse(saved))
                        .build())
                .orElseGet(() -> Response.serverError()
                        .entity(new ErrorResponse("Failed to save customer"))
                        .build());
    }
}
