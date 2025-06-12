package com.infy.user.service;

import com.infy.user.dto.UserDetailsDto;
import com.infy.user.entity.UserDetail;

public interface UserDetailsService {


    UserDetail createUserDetail(UserDetail userDetail);

    UserDetailsDto getUserDetail(Long id);

    void sendOrderStatusMessage(String orderId, String status);
}
