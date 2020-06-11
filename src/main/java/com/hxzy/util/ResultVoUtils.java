package com.hxzy.util;

import com.hxzy.enums.ResultVoEnum;
import com.hxzy.vo.ResultVo;


public class ResultVoUtils {

    public static ResultVo success(Object obj) {
        ResultVo result = new ResultVo();
        result.setCode(ResultVoEnum.SUCCESS.getCode());
        result.setMsg(ResultVoEnum.SUCCESS.getMessage());
        result.setData(obj);

        return result;
    }


    public static ResultVo success() {
        return null;
    }

    public static ResultVo success(Integer code, String message) {
        ResultVo result = new ResultVo();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

}
