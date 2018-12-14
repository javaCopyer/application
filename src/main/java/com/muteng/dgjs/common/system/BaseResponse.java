package com.muteng.dgjs.common.system;


import com.muteng.dgjs.common.constant.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: snowxuyu
 * Date: 2016/8/12
 * Time: 20:02
 */
public abstract class BaseResponse {

    public static final <T> ResponseEntity buildSuccess(T t, Integer code, String message) {
        ResponseEntity result = new ResponseEntity();
        result.setCode(code);
        result.setMsg(message);
        result.setObj(t);
        return result;
    }


}
