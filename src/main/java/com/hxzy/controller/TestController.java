package com.hxzy.controller;

import com.hxzy.dto.OrderDTO;
import com.hxzy.enums.ResultEnum;
import com.hxzy.exception.SellException;
import com.hxzy.form.OrderFormData;
import com.hxzy.service.OrderDetailService;
import com.hxzy.service.OrderService;
import com.hxzy.transform.OrderDataToOrderDTO;
import com.hxzy.util.ResultVoUtils;
import com.hxzy.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService detailService;

    @RequestMapping("/create")
    public ResultVo<Map<String, String>> creatTest(@Valid OrderFormData formData, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.getFieldError().getDefaultMessage());
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderDataToOrderDTO.convert(formData);
        OrderDTO result = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtils.success(map);
    }

    @RequestMapping("/list")
    public ResultVo<List<OrderDTO>> orderList(@RequestParam("openid") String openid,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, pagesize);
        List<OrderDTO> orderDTOS = orderService.findMastersByOpenId(openid, pageRequest);

        return ResultVoUtils.success(orderDTOS);
    }

    @RequestMapping("/details")
    public ResultVo<OrderDTO> details(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId) {
        return ResultVoUtils.success(detailService.findDetail(openid, orderId));
    }

    @RequestMapping("/cancel")
    public ResultVo<Map<String, String>> cancel(@RequestParam("openid") String openid,
                                                @RequestParam("orderId") String orderId) {
        detailService.cancelDetail(openid, orderId);
        return ResultVoUtils.success();
    }

}
