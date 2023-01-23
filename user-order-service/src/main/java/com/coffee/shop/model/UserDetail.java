package com.coffee.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private UserTypeDetail userType;
}