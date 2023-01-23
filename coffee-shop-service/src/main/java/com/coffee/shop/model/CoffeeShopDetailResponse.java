package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoffeeShopDetailResponse extends AppResponse {
    private CoffeeShopDetail coffeeShopDetail;

    public CoffeeShopDetailResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}
