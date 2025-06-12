package com.infy.food.service;

import com.infy.food.entity.FoodOrder;
import com.infy.food.kafka.dto.OrderStatusMessage;

public interface FoodService {
    FoodOrder getFoodOrderById(Long id);

    FoodOrder createFoodOrder(FoodOrder foodOrder);

    void updateFoodOrderStatus(OrderStatusMessage message);
}
