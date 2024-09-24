package com.subria.fi.services;

import com.subria.fi.enums.OrderStatus;
import com.subria.fi.enums.OrderType;
import com.subria.fi.interfaces.InventoryOrderRepository;
import com.subria.fi.model.InventoryOrder;
import com.subria.fi.repositories.InventoryOrderRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class InventoryOrderService {
    private InventoryOrderRepositoryImpl inventoryOrderRepository;

    public InventoryOrderService(InventoryOrderRepositoryImpl inventoryOrderRepository) {
        this.inventoryOrderRepository = inventoryOrderRepository;
    }

    public List<InventoryOrder> getAllInventoryOrderInfo(){
        return inventoryOrderRepository.findAll();
    }
    public Optional<InventoryOrder> getInventoryOrderById(Long id){
        return inventoryOrderRepository.findById(id);
    }
    public List<InventoryOrder> findByType(OrderType type){
        return inventoryOrderRepository.findByType(type);
    }

    public List<InventoryOrder> findByStatus(OrderStatus status){
        return inventoryOrderRepository.findByStatus(status);
    }
    public List<InventoryOrder> findByDate(LocalDateTime date){
        return inventoryOrderRepository.findByDate(date);
    }
    public void createInventoryOrder (InventoryOrder entity){
        inventoryOrderRepository.save(entity);
    }

    public InventoryOrder updateInventoryOrder (InventoryOrder entity){
        return inventoryOrderRepository.update(entity);
    }

    public void deleteInventoryOrder (Long id){
        inventoryOrderRepository.delete(id);
    }
}
