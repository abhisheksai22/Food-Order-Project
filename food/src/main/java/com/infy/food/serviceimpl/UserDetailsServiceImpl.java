package com.infy.food.serviceimpl;

import com.infy.food.client.UserDetailsServiceClient;
import com.infy.food.dto.UserDetailsDto;
import com.infy.food.service.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsServiceClient userDetailsServiceClient;

    public UserDetailsServiceImpl(UserDetailsServiceClient userDetailsServiceClient) {
        this.userDetailsServiceClient = userDetailsServiceClient;
    }

    @Override
    public UserDetailsDto getUserDetailsById(Long id) {
        return userDetailsServiceClient.getUserDetailsById(id);
    }
}
