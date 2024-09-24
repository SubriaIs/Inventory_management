package com.subria.fi.utils;

import com.subria.fi.enums.OrderStatus;
import com.subria.fi.enums.OrderType;

import java.time.LocalDateTime;

public class InventoryOrderValidator {

    public static void validateInventoryOrderIdParameters(Long orderId){
        if (orderId == null || orderId < 1) {
            throw new IllegalArgumentException("Order id must be a positive number");
        }
    }

    public static void validateInventoryOrderTypeParameters(OrderType type){
        if (type == null) {
            throw new IllegalArgumentException("Order type can not be null");
        }
    }

    public static void validateInventoryOrderStatusParameters(OrderStatus status){
        if (status == null) {
            throw new IllegalArgumentException("Order Status can not be null");
        }
    }

    public static void validateInventoryOrderDateParameters(LocalDateTime orderDate){
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date can not be null");
        }
    }

    public static void validateInventoryOrderParameters(OrderType type, int quantity, double unitPrice, LocalDateTime orderDate){
        if (type == null) {
            throw new IllegalArgumentException("Order type can not be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date can not be null");
        }
    }

    public static void validateInventoryOrderAllParameters(Long orderId,OrderType type, int quantity, double unitPrice, LocalDateTime orderDate){
        if (type == null) {
            throw new IllegalArgumentException("Order type can not be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be a positive number");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date can not be null");
        }
    }

}
