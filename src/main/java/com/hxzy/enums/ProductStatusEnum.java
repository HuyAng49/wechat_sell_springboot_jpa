package com.hxzy.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    ON_SHELF(0, "在架"),
    OFF_SHELF(1, "下架");
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
