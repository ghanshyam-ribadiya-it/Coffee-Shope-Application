package com.coffee.shop.users.model;

import com.coffee.shop.users.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDetailListResponse extends AppResponse {
    private List<UserDetail> userDetails;

    public UserDetailListResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}