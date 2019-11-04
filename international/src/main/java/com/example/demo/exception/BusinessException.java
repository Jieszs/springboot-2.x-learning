package com.example.demo.exception;

/**
 * @author zj
 * @date 2019/11/4
 * 业务异常
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
