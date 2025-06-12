package com.infy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long addressId;
    private String addressName;
    private String addressType;
    private String city;

}