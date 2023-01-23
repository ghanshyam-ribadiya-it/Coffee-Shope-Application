package com.coffee.shop.model;

import com.coffee.shop.constants.AppMessage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuResponse extends AppResponse {
    private MenuDetail menuDetail;

    public MenuResponse(AppMessage appMessage) {
        super(appMessage.getResultCode(), appMessage.getMessage(), appMessage.getErrors());
    }
}
