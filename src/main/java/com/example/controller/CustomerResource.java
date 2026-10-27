package com.example.controller;

import com.example.service.CustomerServiceImplementation;
import com.example.view.CustomerResponse;
import com.example.view.ErrorResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST controller for customer lookups.
 *
 * Full path: /api/customer-details/{id}  (the /api prefix comes from
 * MyEEApplication's @ApplicationPath).
 *
 * Thin by design — parse the request, delegate to the model (CustomerService),
 * return a view (CustomerResponse / ErrorResponse). No business logic here.
 */
@Path("/customer-details")
public class CustomerResource {

    // Field injection — CDI resolves the @ApplicationScoped CustomerService.
    @Inject
    private CustomerServiceImplementation customerService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") String id) {
        return customerService.findById(id)
                .map(customer -> Response.ok(new CustomerResponse(customer)).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Customer not found: " + id))
                        .build());
    }
}
