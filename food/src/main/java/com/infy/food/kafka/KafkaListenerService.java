package com.infy.food.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.food.kafka.dto.OrderStatusMessage;
import com.infy.food.serviceimpl.FoodServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaListenerService {

    private final FoodServiceImpl foodService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public KafkaListenerService(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    @KafkaListener(topics = "food-order-status", groupId = "user-details-group")
    public void listen(String message) throws JsonProcessingException {
        OrderStatusMessage orderStatusMessage = objectMapper.readValue(message, OrderStatusMessage.class);
        foodService.updateFoodOrderStatus(orderStatusMessage);
        log.info("Received message from user details service : {}", message);
    }

}