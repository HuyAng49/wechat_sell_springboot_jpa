package com.hxzy.vo;

import lombok.Data;

/**
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午4:00
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

}
