package com.coffee.shop.users.model;

import com.coffee.shop.users.constants.RequestType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private RequestType type;
}
