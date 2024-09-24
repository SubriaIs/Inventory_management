package com.subria.fi.apis;

import com.subria.fi.exceptions.IMServiceException;
import com.subria.fi.model.CustomerInfo;
import com.subria.fi.repositories.CustomerInfoRepositoryImpl;
import com.subria.fi.services.CustomerInfoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/v1")
public class CustomerInfoAPI {

    private CustomerInfoService customerInfoService;

    public CustomerInfoAPI() {
        CustomerInfoRepositoryImpl customerInfoRepository = new CustomerInfoRepositoryImpl();
        this.customerInfoService = new CustomerInfoService(customerInfoRepository);
    }

    @GET
    @Path("/customer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerInfo> getCustomers() {
        List<CustomerInfo> customerInfos = customerInfoService.getAllCustomerInfo();
        if(customerInfos.isEmpty()){
            throw new IMServiceException("Not found",404,"No categories found in database.");
        }else {
            return customerInfos;
        }
    }

    @GET
    @Path("/customer/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Long id) {
        Optional<CustomerInfo> customer = customerInfoService.getCustomerInfoById(id);
        if (customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            throw new IMServiceException("Not found",404,"Customer id not found: "+id);
        }
    }

    @GET
    @Path("/customer/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByName(@PathParam("name") String name) {
        Optional<CustomerInfo> customer = customerInfoService.getCustomerInfoByName(name);
        if (customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            throw new IMServiceException("Not found",404,"Customer name not found: "+name);
        }
    }

    @GET
    @Path("/customer/company_name/{companyName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByCompanyName(@PathParam("companyName") String companyName) {
        Optional<CustomerInfo> customer = customerInfoService.getCustomerInfoByCompanyName(companyName);
        if (customer.isPresent()) {
            return Response.ok(customer.get()).build();
        } else {
            throw new IMServiceException("Not found",404,"Company name not found: "+companyName);
        }
    }

    @POST
    @Path("/customer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addCustomer(CustomerInfo customerInfo) {
        Optional<CustomerInfo> existingCustomerInfo = customerInfoService.getCustomerInfoByName(customerInfo.getName());
        if (existingCustomerInfo.isPresent()) {
            throw new IMServiceException("Already Exist",400,"Duplicate Customer Name!");
        }else{
            customerInfoService.createCustomerInfo(customerInfo);
        }
    }

    @PUT
    @Path("/customer/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") Long id, CustomerInfo customerInfo) {
        Optional<CustomerInfo> existingCustomerInfo = customerInfoService.getCustomerInfoById(id);
        if (existingCustomerInfo.isPresent()) {
            customerInfo.setContactPersonId(id);
            CustomerInfo updatedCustomerInfo = customerInfoService.UpdateCustomerInfo(customerInfo);
            return Response.ok(updatedCustomerInfo).build();
        } else {
            throw new IMServiceException("Not found",404,"Customer id not found: "+id);
        }
    }

    @DELETE
    @Path("/customer/id/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        Optional<CustomerInfo> existingCustomerInfo = customerInfoService.getCustomerInfoById(id);
        if (existingCustomerInfo.isPresent()) {
            customerInfoService.removeCustomerInfo(id);
            return Response.noContent().build();
        } else {
            throw new IMServiceException("Not found",404,"Customer id not found: "+id);
        }
    }
}
