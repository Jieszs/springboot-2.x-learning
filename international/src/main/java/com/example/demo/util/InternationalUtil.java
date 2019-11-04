package com.example.demo.util;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化工具类
 * @author zj
 * @date 2019/11/4
 */
@Component
public class InternationalUtil {
    @Resource
    private HttpServletRequest request;

    private static final String ACCEPT_LANGUAGE ="Accept-Language";
    private static final String FILTER_PREFIX ="#";
    private static final String LANGUAGE_SEPARATOR ="-";
    /**
     * 获取国际化消息
     * @param key 国际化的key
     * @param args 被替换占位符的值
     * 1.带#号的不是key的字符串，直接过滤#号
     * 2.是key的字符串，有占位符的替换
     * 3.不是key的字符串，返回字符串本身
     *
     */
    public  String getInterNationalMsg(String key, Object... args) {
        //带#号的不是key的字符串，直接过滤#号
        if (key.startsWith(FILTER_PREFIX)) {
            return key.substring(1);
        }
        ResourceBundle rb;
        String requestHeader = request.getHeader(ACCEPT_LANGUAGE);
        //是key的字符串，有占位符的替换
        try {
            //如en-US,zh-CN
            if (!StringUtils.isEmpty(requestHeader) && requestHeader.contains(LANGUAGE_SEPARATOR)) {
                rb = ResourceBundle.getBundle("msg", new Locale(requestHeader.split(LANGUAGE_SEPARATOR)[0], requestHeader.split(LANGUAGE_SEPARATOR)[1]));
            } else {
                rb = ResourceBundle.getBundle("msg");
            }
            return MessageFormat.format(rb.getString(key), args);
        } catch (Exception e) {
            //不是key的字符串，返回字符串本身
            return key;
        }
    }
}
