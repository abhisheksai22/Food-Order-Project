package com.infy.user.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "delete-food-order-status", groupId = "food-group")
    public void consume(String message) {
        log.info("This shouldn't happen");

        // Process the message as needed
    }


}
