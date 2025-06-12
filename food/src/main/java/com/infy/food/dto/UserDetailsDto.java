package com.infy.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDetailsDto {

    private Long userDetailID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<AddressDto> addresses;

}
