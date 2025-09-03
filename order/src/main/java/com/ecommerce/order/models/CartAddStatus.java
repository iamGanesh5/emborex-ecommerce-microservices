package com.ecommerce.order.models;

public enum CartAddStatus {
    OUT_OF_STOCK,
    QUANTITY_UPDATED, // instead of ADDED_TO_EXISTING
    ADDED,
    USER_NOT_FOUND
}
