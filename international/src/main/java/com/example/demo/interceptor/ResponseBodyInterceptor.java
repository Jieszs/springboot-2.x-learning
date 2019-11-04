package com.example.demo.interceptor;



import com.example.demo.util.InternationalUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
/**
 * @author zj
 * @date 2019/11/4
 * 全局控制器处理
 */
@ControllerAdvice
public class ResponseBodyInterceptor implements ResponseBodyAdvice {

    @Resource
    private InternationalUtil internationalUtil;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //控制器结果为字符串才进行处理
        if (body instanceof String) {
            return internationalUtil.getInterNationalMsg((String)body);
        }
        return body;
    }
}
