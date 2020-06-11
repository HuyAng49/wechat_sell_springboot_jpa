package com.hxzy.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OrderFormData {

    @NotBlank(message = "名字不能为空！")
    private String name;

    @NotBlank(message = "电话不能为空！")
    @Size(min = 11, max = 11, message = "请输入正确的电话号码！")
    private String phone;

    @NotBlank(message = "地址不能为空！")
    private String address;

    @NotBlank(message = "openid不能为空！")
    private String openid;

    @NotBlank(message = "购物车不能为空！")
    private String items;

}
