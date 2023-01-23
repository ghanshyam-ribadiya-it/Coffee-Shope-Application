package com.coffee.shop.service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.model.UserDetailResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class UsersService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_USER = "userService";

    @CircuitBreaker(name = SERVICE_USER, fallbackMethod = "getUserDetailByIdFallBack")
    public UserDetailResponse getUserDetailById(Long userId) {
        log.info("User service is going to call to retrieve the user detail for the User Id : {}", userId);

        UserDetailResponse userDetailResponse = restTemplate.getForObject("http://USER-SERVICE/user/" + userId, UserDetailResponse.class);
        log.info("Response from the User service is : {}", userDetailResponse.getResultCode());

        return userDetailResponse;
    }

    public UserDetailResponse getUserDetailByIdFallBack(Long userId, Exception e) {
        log.error("Fall back method is called for the user service api");
        UserDetailResponse userDetailResponse = new UserDetailResponse(ErrorAppMessage.EXCEPTION_IN_USER_SERVICE);
        return userDetailResponse;
    }
}