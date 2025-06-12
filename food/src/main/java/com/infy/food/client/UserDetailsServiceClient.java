package com.infy.food.client;

import com.infy.food.config.WebClientConfig;
import com.infy.food.dto.UserDetailsDto;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceClient {

    private final WebClientConfig webClientConfig;

    public UserDetailsServiceClient(WebClientConfig webClientConfig) {
        this.webClientConfig = webClientConfig;
    }

    public UserDetailsDto getUserDetailsById(Long id) {
        return webClientConfig.webClientBuilder()
                .baseUrl("http://USER-DETAILS-SERVICE")
                .build()
                .get()
                .uri("/v1/users/{id}", id)
                .retrieve()
                .bodyToMono(UserDetailsDto.class)
                .block();
    }


}
