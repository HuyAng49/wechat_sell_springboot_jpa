package com.hxzy.service;


import com.hxzy.dto.OrderDTO;

public interface OrderDetailService {

    //查找订单详情
    OrderDTO findDetail(String openId, String orderId);

    //取消订单详情
    OrderDTO cancelDetail(String openId, String orderId);

}
