package com.hxzy.service.impl;

import com.hxzy.dto.CartDTO;
import com.hxzy.dto.OrderDTO;
import com.hxzy.enums.OrderStatusEnum;
import com.hxzy.enums.PayEnum;
import com.hxzy.enums.ResultEnum;
import com.hxzy.exception.SellException;
import com.hxzy.pojo.OrderDetail;
import com.hxzy.pojo.OrderMaster;
import com.hxzy.pojo.ProductInfo;
import com.hxzy.repository.OrderDetailRepository;
import com.hxzy.repository.OrderMasterRepository;
import com.hxzy.service.OrderService;
import com.hxzy.service.ProductInfoService;
import com.hxzy.transform.OrderMasterToOrderDTO;
import com.hxzy.util.keyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService infoService;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderMasterRepository masterRepository;

    /**
     * 创建订单
     *
     * @param orderDTO
     **/
    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        //商品总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        String orderId = keyUtil.generateKey();
        //1.查询商品
        ProductInfo info = null;
        for (OrderDetail detail : orderDTO.getDetailList()) {
            info = infoService.findById(detail.getProductId());
            if (info == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算总价
            orderAmount = info.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity())).add(orderAmount);

            //3.保存订单详情到数据库
            detail.setDetailId(keyUtil.generateKey());
            detail.setOrderId(orderId);
            BeanUtils.copyProperties(info, detail);
            detailRepository.save(detail);

        }
        OrderMaster master = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, master);
        master.setOrderAmount(orderAmount);
        master.setOrderStatus(OrderStatusEnum.ORDER_NEW.getCode());
        master.setPayStatus(PayEnum.WAIT_PAY.getCode());
        masterRepository.save(master);

        //4.减少库存
        List<CartDTO> cartDTOList = orderDTO.getDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        infoService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    /**
     * 查询单个订单
     *
     * @param orderId
     **/
    @Override
    public OrderDTO findById(String orderId) {

        OrderMaster master = masterRepository.findById(orderId).get();
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> detailList = detailRepository.findByOrderId(master.getOrderId());
        if (CollectionUtils.isEmpty(detailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setDetailList(detailList);

        return orderDTO;
    }

    /**
     * 查询订单列表
     *
     * @param buyerOpenId
     * @param pageable
     **/
    @Override
    public List<OrderDTO> findMastersByOpenId(String buyerOpenId, Pageable pageable) {
        List<OrderMaster> masterList = masterRepository.findByBuyerOpenid(buyerOpenId, pageable).getContent();
        List<OrderDTO> dtoList = OrderMasterToOrderDTO.convert(masterList);
        return dtoList;
    }

    /**
     * 取消订单
     *
     * @param orderDTO
     **/
    @Override
    public OrderDTO cancelOrder(OrderDTO orderDTO) {
        //判断订单状态
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.ORDER_NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.ORDER_CANCEL.getCode());
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, master);
        OrderMaster masterResult = masterRepository.save(master);
        if (masterResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", master);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        List<CartDTO> cartDTOList = orderDTO.getDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        infoService.increaseStock(cartDTOList);

        //如果已支付要退款
        if (orderDTO.getPayStatus().equals(PayEnum.SUCCESS_PAY.getCode())) {

        }
        return null;
    }

    /**
     * 支付订单
     *
     * @param orderDTO
     **/
    @Override
    public OrderDTO payOrder(OrderDTO orderDTO) {

        return null;
    }

    /**
     * 完成订单
     *
     * @param orderDTO
     * @return com.hxzy.dto.OrderDTO
     * @author hu.Yang
     * @date 22/5/20 下午9:40
     */
    @Override
    public OrderDTO finishOrder(OrderDTO orderDTO) {

        //判断订单状态 新订单才能完结
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.ORDER_NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDTO.setOrderStatus(OrderStatusEnum.ORDER_FINISHED.getCode());
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, master);
        OrderMaster updateMaster = masterRepository.save(master);
        if (updateMaster == null) {
            log.error("【完结订单】更新失败, orderMaster={}", master);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
