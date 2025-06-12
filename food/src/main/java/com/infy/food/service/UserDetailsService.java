package com.infy.food.service;

import com.infy.food.dto.UserDetailsDto;

public interface UserDetailsService {
    UserDetailsDto getUserDetailsById(Long id);
}
