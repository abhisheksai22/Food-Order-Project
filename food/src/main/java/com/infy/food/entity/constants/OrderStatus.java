package com.infy.food.entity.constants;

public enum OrderStatus {

    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    PREPARING("Preparing"),
    READY_FOR_PICKUP("Ready for Pickup"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
