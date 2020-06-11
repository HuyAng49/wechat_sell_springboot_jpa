package com.hxzy.service.impl;

import com.hxzy.dto.OrderDTO;
import com.hxzy.enums.ResultEnum;
import com.hxzy.exception.SellException;
import com.hxzy.pojo.OrderDetail;
import com.hxzy.repository.OrderDetailRepository;
import com.hxzy.service.OrderDetailService;
import com.hxzy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 22/5/20 下午3:21
 */
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findDetail(String openId, String orderId) {

        OrderDTO orderDTO = orderService.findById(orderId);

        List<OrderDetail> detailList = detailRepository.findByOrderId(orderId);
        if (detailList == null) {
            log.info(ResultEnum.ORDERDETAIL_NOT_EXIST.getMessage());
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        } else if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("[查询订单] 订单openid不一致,openid={},orderId={}", openId, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        orderDTO.setDetailList(detailList);

        return orderDTO;
    }

    @Override
    public OrderDTO cancelDetail(String openId, String orderId) {
        OrderDTO orderDTO = findDetail(openId, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancelOrder(orderDTO);
    }
}
