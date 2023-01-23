package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeShopDetailResponse extends AppResponse {
    private CoffeeShopDetail coffeeShopDetail;

    public CoffeeShopDetailResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}
