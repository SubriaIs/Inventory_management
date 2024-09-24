package com.subria.fi.repositories;

import com.subria.fi.enums.OrderStatus;
import com.subria.fi.enums.OrderType;
import com.subria.fi.model.InventoryOrder;
import com.subria.fi.utils.InventoryOrderValidator;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class InventoryOrderRepositoryImpl implements com.subria.fi.interfaces.InventoryOrderRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("inventoryManagementPU");
    protected static final Logger logger = LogManager.getLogger(InventoryOrderRepositoryImpl.class);

    @Override
    public List<InventoryOrder> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<InventoryOrder> query = em.createQuery("SELECT s FROM InventoryOrder s", InventoryOrder.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    @Override
    public Optional<InventoryOrder> findById(Long id) {
        InventoryOrderValidator.validateInventoryOrderIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(InventoryOrder.class, id));
        } finally {
            em.close();
        }

    }

    @Override
    public List<InventoryOrder> findByType(OrderType type) {
        InventoryOrderValidator.validateInventoryOrderTypeParameters(type);
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<InventoryOrder> query = em.createQuery("SELECT s FROM InventoryOrder s WHERE s.orderType = :type", InventoryOrder.class);
            query.setParameter("type", type);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<InventoryOrder> findByStatus(OrderStatus status) {
        InventoryOrderValidator.validateInventoryOrderStatusParameters(status);
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<InventoryOrder> query = em.createQuery("SELECT s FROM InventoryOrder s WHERE s.status = :status", InventoryOrder.class);
            query.setParameter("status", status);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<InventoryOrder> findByDate(LocalDateTime date) {
        InventoryOrderValidator.validateInventoryOrderDateParameters(date);
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<InventoryOrder> query = em.createQuery("SELECT s FROM InventoryOrder s WHERE s.orderDate = :date", InventoryOrder.class);
            query.setParameter("date", date);
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void save(InventoryOrder inventoryOrder) {
        InventoryOrderValidator.validateInventoryOrderParameters(inventoryOrder.getOrderType(),inventoryOrder.getQuantity(),inventoryOrder.getUnitPrice(),inventoryOrder.getOrderDate());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (inventoryOrder.getOrderId() == null) {
                em.persist(inventoryOrder);
            } else {
                logger.error(inventoryOrder.getOrderId() + "Id already exist. ");
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public InventoryOrder update(InventoryOrder inventoryOrder) {
        InventoryOrderValidator.validateInventoryOrderAllParameters(inventoryOrder.getOrderId(), inventoryOrder.getOrderType(),inventoryOrder.getQuantity(),inventoryOrder.getUnitPrice(),inventoryOrder.getOrderDate());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (inventoryOrder.getOrderId() != null) {
                inventoryOrder = em.merge(inventoryOrder);
            } else {
                logger.error(inventoryOrder.getOrderId() + "Id not found. ");
            }
            em.getTransaction().commit();
            return inventoryOrder;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        InventoryOrderValidator.validateInventoryOrderIdParameters(id);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            InventoryOrder inventoryOrder = em.find(InventoryOrder.class, id);
            if (inventoryOrder != null) {
                em.remove(inventoryOrder);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
