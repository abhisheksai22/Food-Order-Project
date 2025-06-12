package com.infy.user.controller;

import com.infy.user.dto.UserDetailsDto;
import com.infy.user.entity.UserDetail;
import com.infy.user.service.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail) {
        log.info("User email: {}, user address: {}", userDetail.getEmail(),userDetail.getAddresses().get(0).getAddressName());
        return ResponseEntity.ok(userDetailsService.createUserDetail(userDetail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUser(@PathVariable("id") Long id) {
        log.info("Fetching user details for ID: {}", id);
        return ResponseEntity.ok(userDetailsService.getUserDetail(id));
    }

    @PatchMapping
    public ResponseEntity<?> sendKafkaOrderStatusMessage(@RequestParam String orderId,@RequestParam String status) {
        log.info("Sending Kafka message for order ID: {}, status: {}", orderId, status);
        // Assuming there's a method in userDetailsService to handle this
        userDetailsService.sendOrderStatusMessage(orderId, status);
        return ResponseEntity.ok("Order status message sent successfully");
    }


}
