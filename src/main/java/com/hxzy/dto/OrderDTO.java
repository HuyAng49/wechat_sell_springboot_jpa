package com.hxzy.dto;

import com.hxzy.enums.OrderStatusEnum;
import com.hxzy.enums.PayEnum;
import com.hxzy.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    /**
     * 买家id
     **/
    private String orderId;

    /**
     * 买家名字
     **/
    private String buyerName;

    /**
     * 买家电话
     **/
    private String buyerPhone;

    /**
     * 买家地址
     **/
    private String buyerAddress;

    /**
     * 买家openId
     **/
    private String buyerOpenid;

    /**
     * 订单总金额
     **/
    private BigDecimal orderAmount;

    /**
     * 订单状态
     **/
    private Integer orderStatus = OrderStatusEnum.ORDER_NEW.getCode();

    /**
     * 订单支付状态
     **/
    private Integer payStatus = PayEnum.WAIT_PAY.getCode();

    /**
     * 订单创建时间
     **/
    private Date createTime;

    /**
     * 订单更新时间
     **/
    private Date updateTime;

    /**
     * 相关详细订单列表
     **/
    private List<OrderDetail> detailList;
}
