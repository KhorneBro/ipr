package ru.veselkov;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.veselkov.dto.CustomerDto;
import ru.veselkov.dto.ProductDto;
import ru.veselkov.dto.RegistrationUser;
import ru.veselkov.service.cdi.CallCdiListInterfaceService;
import ru.veselkov.service.cdi.CallCdiNamedBeanService;
import ru.veselkov.service.cdi.CallCdiService;
import ru.veselkov.service.remote.RemoteCallService;
import ru.veselkov.service.ejb.SimpleService;

import javax.naming.NamingException;

@Path("/service")
public class ServiceController {

    @EJB(lookup = "java:global/ipr/ipr-ejb/SimpleServiceBean!ru.veselkov.service.ejb.SimpleService")
    SimpleService service;

    @EJB
    private RemoteCallService remoteCallService;

    @Inject
    private CallCdiService callService;

    @Inject
    private CallCdiListInterfaceService callCdiInterfaceService;

    @Inject
    private CallCdiNamedBeanService callCdiNamedBeanService;

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

    @Path("/remote")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response remote() {
        try {
            remoteCallService.call();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return Response.ok().build();
    }

    @Path("/remote/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response remote(@PathParam("id") long id) {
        try {
            remoteCallService.call(id);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return Response.ok().build();
    }

    @Path("/cdi")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response cdi() {
        callService.call();
        return Response.ok().build();
    }

    @Path("/cdi/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response cdiList() {
        callCdiInterfaceService.call();
        return Response.ok().build();
    }

    @Path("/cdi/name")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response cdiName() {
        callCdiNamedBeanService.call();
        return Response.ok().build();
    }

    @Path("/create/customer/trans/{method}")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createCustomerTrans(@PathParam("method") String method, RegistrationUser registrationUser) {
        service.createCustomerTrans(registrationUser, method);
        return Response.ok().build();
    }
}
