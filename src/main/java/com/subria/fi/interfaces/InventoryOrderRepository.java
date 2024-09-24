package com.subria.fi.interfaces;

import com.subria.fi.enums.OrderStatus;
import com.subria.fi.enums.OrderType;
import com.subria.fi.model.InventoryOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InventoryOrderRepository {
    List<InventoryOrder> findAll();
    Optional<InventoryOrder> findById(Long id);
    List<InventoryOrder> findByType(OrderType type);
    List<InventoryOrder> findByStatus(OrderStatus status);
    List<InventoryOrder> findByDate(LocalDateTime date);
    void save(InventoryOrder entity);

    InventoryOrder update(InventoryOrder entity);

    void delete(Long id);
}
