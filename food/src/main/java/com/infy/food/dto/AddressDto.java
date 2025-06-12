package com.infy.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDto {

    private Long addressId;
    private String addressName;
    private String addressType;
    private String city;

}
