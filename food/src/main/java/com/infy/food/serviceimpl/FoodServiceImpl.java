package com.infy.food.serviceimpl;

import com.infy.food.entity.FoodOrder;
import com.infy.food.entity.constants.OrderStatus;
import com.infy.food.kafka.KafkaProduceService;
import com.infy.food.kafka.dto.OrderStatusMessage;
import com.infy.food.repository.FoodOrderRepository;
import com.infy.food.repository.MenuItemRepository;
import com.infy.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodOrderRepository foodOrderRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final KafkaProduceService kafkaProduceService;
    private final MenuItemRepository menuItemRepository;

    public FoodServiceImpl(FoodOrderRepository foodOrderRepository,
                           UserDetailsServiceImpl userDetailsService,
                           KafkaProduceService kafkaProduceService,
                           MenuItemRepository menuItemRepository) {
        this.foodOrderRepository = foodOrderRepository;
        this.userDetailsService = userDetailsService;
        this.kafkaProduceService = kafkaProduceService;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public FoodOrder getFoodOrderById(Long id) {
        return foodOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food order not found with id: " + id));
    }

    @Override
    @Transactional
    public FoodOrder createFoodOrder(FoodOrder foodOrder) {
        if (!isUserPresent(foodOrder.getUserDetailId())) {
            throw new RuntimeException("User not found with id: " + foodOrder.getUserDetailId());
        }
        if(foodOrder.getOrderItems() != null) {
            foodOrder.setTotalPrice(calculateTotalPrice(foodOrder));
        }
        foodOrder.setOrderStatus(OrderStatus.valueOf("ACCEPTED"));
        kafkaProduceService.sendMessage(foodOrder.getOrderStatus().toString());
        return foodOrderRepository.save(foodOrder);
    }

    @Override
    public void updateFoodOrderStatus(OrderStatusMessage message) {
        FoodOrder foodOrder = getFoodOrderById(Long.parseLong(message.getOrderId()));
        foodOrder.setOrderStatus(OrderStatus.valueOf(message.getStatus()));
        foodOrderRepository.save(foodOrder);
        log.info("Kafka message processed: Order ID = {}, Status = {}", message.getOrderId(), message.getStatus());
    }

    private boolean isUserPresent(Long userId) {
        return userDetailsService.getUserDetailsById(userId) != null;
    }

    private double calculateTotalPrice(FoodOrder foodOrder) {
        return foodOrder.getOrderItems().stream()
                .mapToDouble(item ->  {
                    double price  = menuItemRepository.findById(item.getMenuItem().getMenuItemId())
                            .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + item.getMenuItem().getMenuItemId()))
                            .getPrice();
                    return price * item.getQuantity();
                }).sum();
    }

}
