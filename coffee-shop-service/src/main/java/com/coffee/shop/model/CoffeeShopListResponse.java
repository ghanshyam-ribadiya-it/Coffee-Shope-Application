package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CoffeeShopListResponse extends AppResponse {
    private List<CoffeeShopDetail> coffeeShopDetails;

    public CoffeeShopListResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}
