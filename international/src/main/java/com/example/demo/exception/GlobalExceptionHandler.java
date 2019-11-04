package com.example.demo.exception;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.InternationalUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author zj
 * @date 2019/11/4
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private InternationalUtil internationalUtil;

    /**
     * 400,用来处理bean字段异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity resolveConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 400,用来处理方法参数异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);

        }
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 400
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 403
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(Exception e) {
        return buildResponse(e.getMessage(), HttpStatus.FORBIDDEN);
    }


    /**
     * 构建响应实体
     *
     * @param msg
     * @param status
     * @return
     */
    private ResponseEntity buildResponse(String msg, HttpStatus status) {
        return buildResponse(msg, status, null);
    }

    /**
     * 构建响应实体
     *
     * @param msg
     * @param status
     * @return
     */
    private ResponseEntity buildResponse(String msg, HttpStatus status, String result) {
        JSONObject body = new JSONObject();
        body.put("status", status.value());
        body.put("error", status);
        body.put("message", internationalUtil.getInterNationalMsg(msg));
        return new ResponseEntity(body, status);
    }
}
