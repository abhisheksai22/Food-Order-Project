package com.infy.food.controller;

import com.infy.food.entity.OrderItem;
import com.infy.food.repository.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/OrderItems")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.ok(savedOrderItem);
    }


}
