package com.muteng.dgjs.handler;

import com.muteng.dgjs.common.system.BaseResponse;

import com.muteng.dgjs.common.system.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * User: snowxuyu
 * Date: 2017-8-24
 * Time: 21:58
 */
@RestControllerAdvice
public class DemoExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({Exception.class})
    public ResponseEntity exceptionHandler(Exception e) {
        logger.error("Controller Exception:{}", e);
        return BaseResponse.buildSuccess("系统异常", 0, "失败");
    }
}
