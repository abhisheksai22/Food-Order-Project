package com.infy.food.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProduceService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProduceService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("delete-food-order-status", message);
    }

}
