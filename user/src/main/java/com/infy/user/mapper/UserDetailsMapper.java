package com.infy.user.mapper;

import com.infy.user.dto.AddressDto;
import com.infy.user.dto.UserDetailsDto;
import com.infy.user.entity.Address;
import com.infy.user.entity.UserDetail;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserDetailsMapper {
    
    public static UserDetailsDto toUserDetailsDto(UserDetail userDetail) {
        return UserDetailsDto.builder()
                .userDetailID(userDetail.getUserDetailID())
                .firstName(userDetail.getFirstName())
                .lastName(userDetail.getLastName())
                .email(userDetail.getEmail())
                .phoneNumber(userDetail.getPhoneNumber())
                .addresses(userDetail.getAddresses() != null ?
                        toAddressDtos(userDetail.getAddresses()) : null)
                .build();
    }

    private static List<AddressDto> toAddressDtos(List<Address> addresses) {
        return addresses.stream()
                .map(address -> AddressDto.builder()
                        .addressId(address.getAddressId())
                        .addressName(address.getAddressName())
                        .addressType(String.valueOf(address.getAddressType()))
                        .city(address.getCity())
                        .build())
                .toList();
    }

}
