package com.infy.user.kafka.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusMessage implements Serializable {

    private String orderId;
    private String status;

}
