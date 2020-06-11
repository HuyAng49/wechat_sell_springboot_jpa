package com.hxzy.transform;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hxzy.dto.OrderDTO;
import com.hxzy.enums.ResultEnum;
import com.hxzy.exception.SellException;
import com.hxzy.form.OrderFormData;
import com.hxzy.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderDataToOrderDTO {

    public static OrderDTO convert(OrderFormData formData) {

        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(formData.getName());
        orderDTO.setBuyerPhone(formData.getPhone());
        orderDTO.setBuyerAddress(formData.getAddress());
        orderDTO.setBuyerOpenid(formData.getOpenid());

        List<OrderDetail> detailList = null;
        try {
            detailList = gson.fromJson(formData.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", formData.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setDetailList(detailList);

        return orderDTO;
    }

}
