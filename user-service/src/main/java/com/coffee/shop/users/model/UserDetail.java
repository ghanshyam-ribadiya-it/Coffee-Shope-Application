package com.coffee.shop.users.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetail {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private UserTypeDetail userType;
}