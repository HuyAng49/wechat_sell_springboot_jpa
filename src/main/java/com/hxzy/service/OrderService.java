package com.hxzy.service;

import com.hxzy.dto.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午8:52
 */
public interface OrderService {

    /**
     * 创建订单
     **/
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * 查询单个订单
     **/
    OrderDTO findById(String orderId);

    /**
     * 查询订单列表
     **/
    List<OrderDTO> findMastersByOpenId(String buyerOpenId, Pageable pageable);

    /**
     * 取消订单
     **/
    OrderDTO cancelOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     **/
    OrderDTO payOrder(OrderDTO orderDTO);

    /**
     * 完成订单
     **/
    OrderDTO finishOrder(OrderDTO orderDTO);

}
