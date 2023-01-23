package com.coffee.shop.users.service;

import com.coffee.shop.users.constants.ErrorAppMessage;
import com.coffee.shop.users.constants.SuccessAppMessage;
import com.coffee.shop.users.domain.User;
import com.coffee.shop.users.exception.ShopException;
import com.coffee.shop.users.model.*;
import com.coffee.shop.users.repository.UserRepository;
import com.coffee.shop.users.repository.UserTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsersService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    public UserDetailResponse createUser(UserRequest userRequest) {
        log.info("Request to create User");

        User user = modelMapper.map(userRequest, User.class);
        user.setUserType(userTypeRepository.findByName(userRequest.getType().name()));
        userRepository.save(user);

        UserDetailResponse userDetailResponse = new UserDetailResponse(SuccessAppMessage.OK_User_Saved);
        userDetailResponse.setUserDetail(mapUserToUserDetails(user));
        return userDetailResponse;
    }

    private UserDetail mapUserToUserDetails(User user) {
        UserDetail userDetail = modelMapper.map(user, UserDetail.class);
        userDetail.setUserType(new UserTypeDetail(user.getUserType().getId(), user.getUserType().getName()));
        return userDetail;
    }

    public UserDetailResponse getUserById(Long userId) {
        log.info("Find User by userId : {}", userId);

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserDetailResponse userDetailResponse = new UserDetailResponse(SuccessAppMessage.OK_User_Details);
            userDetailResponse.setUserDetail(mapUserToUserDetails(user.get()));
            return userDetailResponse;
        }

        throw new ShopException(ErrorAppMessage.USER_NOT_FOUND);
    }

    public UserDetailListResponse getAllUsers() {
        log.info("Request to fetch all users");

        List<UserDetail> userDetails = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDetails.add(mapUserToUserDetails(user));
        }

        UserDetailListResponse userDetailListResponse = new UserDetailListResponse(SuccessAppMessage.OK_User_Details);
        userDetailListResponse.setUserDetails(userDetails);
        return userDetailListResponse;
    }
}