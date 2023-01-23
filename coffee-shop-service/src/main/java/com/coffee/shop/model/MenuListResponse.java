package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuListResponse extends AppResponse {
    private List<MenuDetail> menuDetails;

    public MenuListResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}
