package com.hxzy.pojo;

import com.hxzy.enums.OrderStatusEnum;
import com.hxzy.enums.PayEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class OrderMaster {

    /**
     * 买家id
     **/
    @Id
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

}
