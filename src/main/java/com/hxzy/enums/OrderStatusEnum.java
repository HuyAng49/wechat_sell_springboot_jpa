package com.hxzy.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午7:22
 */
@Getter
public enum OrderStatusEnum {
    ORDER_NEW(0, "创建订单"),
    ORDER_FINISHED(1, "完结订单"),
    ORDER_CANCEL(3, "取消订单");
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
