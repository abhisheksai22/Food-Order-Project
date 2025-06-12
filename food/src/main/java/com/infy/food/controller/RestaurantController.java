package com.infy.food.controller;

import com.infy.food.entity.Restaurant;
import com.infy.food.repository.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
        return restaurantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        restaurant.getMenuItems().forEach(menuItem -> menuItem.setRestaurant(restaurant));
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

}
