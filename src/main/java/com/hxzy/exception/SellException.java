package com.hxzy.exception;

import com.hxzy.enums.ResultEnum;

/**
 * 自定义异常
 *
 * @author huYang
 * @creed: Talk is cheap,show me the code
 * @date 21/5/20 下午9:24
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
