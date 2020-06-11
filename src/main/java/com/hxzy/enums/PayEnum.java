package com.hxzy.enums;

import lombok.Getter;

@Getter
public enum PayEnum {

    WAIT_PAY(0, "等待支付"),
    SUCCESS_PAY(1, "支付完成");

    private Integer code;

    private String message;

    PayEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
