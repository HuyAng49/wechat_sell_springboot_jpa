package com.hxzy.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(0, "商品不存在"),
    PRODUCT_SHORTAGE(1, "商品库存不足"),
    SUCCSSES(3, "操作成功"),
    PARAM_ERROR(4, "参数错误"),
    ORDER_NOT_EXIST(5, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(6, "订单详情不存在"),
    ORDER_OWNER_ERROR(7, "订单openid不一致"),
    ORDER_STATUS_ERROR(8, "订单状态错误"),
    ORDER_UPDATE_FAIL(9, "更新失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
