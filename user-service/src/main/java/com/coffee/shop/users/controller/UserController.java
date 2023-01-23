package com.coffee.shop.users.controller;

import com.coffee.shop.users.model.UserDetailListResponse;
import com.coffee.shop.users.model.UserDetailResponse;
import com.coffee.shop.users.model.UserRequest;
import com.coffee.shop.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public UserDetailResponse createUser(@RequestBody UserRequest userRequest) {
        return usersService.createUser(userRequest);
    }

    @GetMapping("{id}")
    public UserDetailResponse getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @GetMapping
    public UserDetailListResponse getAllUsers() {
        return usersService.getAllUsers();
    }
}