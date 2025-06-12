package com.infy.user.serviceimpl;

import com.infy.user.dto.UserDetailsDto;
import com.infy.user.entity.UserDetail;
import com.infy.user.kafka.KafkaProducer;
import com.infy.user.kafka.dto.OrderStatusMessage;
import com.infy.user.mapper.UserDetailsMapper;
import com.infy.user.repo.UserDetailRepository;
import com.infy.user.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;
    private final KafkaProducer kafkaProducer;

    public UserDetailsServiceImpl(UserDetailRepository userDetailRepository, KafkaProducer kafkaProducer) {
        this.userDetailRepository = userDetailRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public UserDetail createUserDetail(UserDetail userDetail) {
        if (userDetail.getAddresses() != null) {
            userDetail.getAddresses().forEach(address ->
                    address.setUserDetail(userDetail));
        }
        return userDetailRepository.save(userDetail);
    }

    @Override
    @Cacheable(value = "userDetailsDto", key = "#id")
    public UserDetailsDto getUserDetail(Long id) {
        UserDetail userDetail = userDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        log.info("UserDetails : {}", userDetail);
        return UserDetailsMapper.toUserDetailsDto(userDetail);
    }

    @Override
    public void sendOrderStatusMessage(String orderId, String status) {
        OrderStatusMessage orderStatusMessage = OrderStatusMessage.builder()
                .orderId(orderId)
                .status(status)
                .build();
        try {
            kafkaProducer.sendOrderStatusMessage(orderStatusMessage);
        } catch (Exception e) {
            log.error("Failed to send order status message", e);
        }

    }
}
