package com.coffee.shop.users.model;

import com.coffee.shop.users.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailResponse extends AppResponse {
    private UserDetail userDetail;

    public UserDetailResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}