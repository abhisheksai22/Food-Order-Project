package com.infy.food.controller;

import com.infy.food.entity.MenuItem;
import com.infy.food.repository.MenuItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/MenuItems")
public class MenuItemController {

    private final MenuItemRepository menuItemRepository;


    public MenuItemController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable("id") Long id) {
        return menuItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return ResponseEntity.ok(savedMenuItem);
    }


}
