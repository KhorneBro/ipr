package ru.veselkov;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;
import ru.veselkov.service.SimpleService;

@Path("/service")
public class ServiceController {

    //    @EJB(lookup = "java:global/ipr/ipr-ejb/SimpleServiceBean!ru.veselkov.service.SimpleService")
    @Inject
    SimpleService service;

    @Path("/create/customer")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createCustomer(RegistrationUser registrationUser) {
        service.createCustomer(registrationUser);
        return Response.ok().build();
    }

    @Path("/create/product")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createProduct(ProductDto productDto) {
        service.createProduct(productDto);
        return Response.ok().build();
    }

    @Path("/find/customer/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createProduct(@PathParam("id") int id) {
        CustomerDto customerDto = service.findById(id);
        return Response.ok(customerDto).build();
    }
}
