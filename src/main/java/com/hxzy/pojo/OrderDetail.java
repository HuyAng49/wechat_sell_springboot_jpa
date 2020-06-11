package com.hxzy.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午7:42
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /**
     * 订单id
     **/
    private String orderId;

    /**
     * 商品id
     **/
    private String productId;

    /**
     * 商品名称
     **/
    private String productName;

    /**
     * 商品单价
     **/
    private BigDecimal productPrice;

    /**
     * 商品数量
     **/
    private Integer productQuantity;

    /**
     * 商品小图
     **/
    private String productIcon;

}
