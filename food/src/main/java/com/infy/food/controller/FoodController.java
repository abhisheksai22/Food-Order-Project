package com.infy.food.controller;

import com.infy.food.entity.FoodOrder;
import com.infy.food.serviceimpl.FoodServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order-food")
@Slf4j
public class FoodController {

    private final FoodServiceImpl foodService;

    public FoodController(FoodServiceImpl foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getFoodOrderById(@PathVariable("id") Long id,
                                                      @RequestHeader("Authorization") String authorization,
                                                      @RequestHeader("x-request-id") String requestId) {
        if (requestId == null || requestId.isEmpty()) {

        }
        log.info("Fetching food order with ID: {}", id);
        return new ResponseEntity<>(foodService.getFoodOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FoodOrder> createFoodOrder(@RequestBody FoodOrder foodOrder) {
        log.info("Creating food order: {}", foodOrder);
        FoodOrder createdOrder = foodService.createFoodOrder(foodOrder);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}

