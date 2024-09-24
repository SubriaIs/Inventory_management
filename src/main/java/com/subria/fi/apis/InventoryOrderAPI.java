package com.subria.fi.apis;


import com.subria.fi.enums.OrderStatus;
import com.subria.fi.enums.OrderType;
import com.subria.fi.exceptions.IMServiceException;
import com.subria.fi.model.InventoryOrder;
import com.subria.fi.repositories.InventoryOrderRepositoryImpl;
import com.subria.fi.services.InventoryOrderService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Path("/v1")
public class InventoryOrderAPI {
    private final InventoryOrderService inventoryOrderService;

    public InventoryOrderAPI() {
        InventoryOrderRepositoryImpl inventoryOrderRepository = new InventoryOrderRepositoryImpl();
        this.inventoryOrderService = new InventoryOrderService(inventoryOrderRepository);
    }

    @GET
    @Path("/inventoryOrder")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InventoryOrder> getInventoryOrders() {
        List<InventoryOrder> inventoryOrders = inventoryOrderService.getAllInventoryOrderInfo();
        if(inventoryOrders.isEmpty()){
            throw new IMServiceException("Not found",404,"No inventory orders found in database.");
        }else {
            return inventoryOrders;
        }
    }

    @GET
    @Path("/inventoryOrder/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryOrder(@PathParam("id") Long id) {
        Optional<InventoryOrder> inventoryOrder = inventoryOrderService.getInventoryOrderById(id);
        if (inventoryOrder.isPresent()) {
            return Response.ok(inventoryOrder.get()).build();
        } else {
            throw new IMServiceException("Not found",404,"Inventory order id not found: "+id);
        }
    }

    @GET
    @Path("/inventoryOrder/date")
    @Produces(MediaType.APPLICATION_JSON)
    public List <InventoryOrder>getInventoryOrderByDate (@QueryParam("date") String date) {
        List<InventoryOrder> inventoryOrder = inventoryOrderService.findByDate(LocalDateTime.parse(date));
        if (inventoryOrder.isEmpty()) {
            throw new IMServiceException("Not found",404,"No inventory orders found in database.");
        }else {
            return inventoryOrder;
        }
    }

    @GET
    @Path("/inventoryOrder/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <InventoryOrder> getInventoryOrderByType (@PathParam("type") OrderType type ) {
        List<InventoryOrder> inventoryOrder = inventoryOrderService.findByType(type);
        if (inventoryOrder.isEmpty()) {
            throw new IMServiceException("Not found",404,"No inventory orders found in database.");
        }else {
            return inventoryOrder;
        }
    }

    @GET
    @Path("/inventoryOrder/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public List <InventoryOrder> getInventoryOrderByStatus (@PathParam("status")OrderStatus status) {
        List<InventoryOrder> inventoryOrder = inventoryOrderService.findByStatus(status);
        if (inventoryOrder.isEmpty()) {
            throw new IMServiceException("Not found",404,"No inventory orders found in database.");
        }else {
            return inventoryOrder;
        }
    }

    @POST
    @Path("/inventoryOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addInventoryOrder(InventoryOrder inventoryOrder) {
        inventoryOrderService.createInventoryOrder(inventoryOrder);
    }

    @PUT
    @Path("/inventoryOrder/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryOrder(@PathParam("id") Long id, InventoryOrder inventoryOrder) {
        Optional<InventoryOrder> existingInventoryOrderInfo = inventoryOrderService.getInventoryOrderById(id);
        if (existingInventoryOrderInfo.isPresent()) {
            inventoryOrder.setOrderId(id);
            InventoryOrder updatedInventoryOrder = inventoryOrderService.updateInventoryOrder(inventoryOrder);
            return Response.ok(updatedInventoryOrder).build();
        } else {
            throw new IMServiceException("Not found",404,"Inventory order id not found: "+id);
        }
    }

    @DELETE
    @Path("/inventoryOrder/id/{id}")
    public Response deleteInventoryOrder(@PathParam("id") Long id) {
        Optional<InventoryOrder> existingInventoryOrderInfo = inventoryOrderService.getInventoryOrderById(id);
        if (existingInventoryOrderInfo.isPresent()) {
            inventoryOrderService.deleteInventoryOrder(id);
            return Response.noContent().build();
        } else {
            throw new IMServiceException("Not found",404,"Inventory order id not found: "+id);
        }
    }
}
