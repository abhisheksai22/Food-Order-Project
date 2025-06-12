package com.infy.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDetailsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userDetailID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<AddressDto> addresses;

}