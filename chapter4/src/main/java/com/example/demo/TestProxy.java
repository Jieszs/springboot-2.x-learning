package com.example.demo;

import com.example.demo.intercept.MyInterceptor;
import com.example.demo.proxy.ProxyBean;
import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;

public class TestProxy {
    public static void main(String[] args) {
        HelloService helloService =new HelloServiceImpl();
        //按约定获取proxy
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService,new MyInterceptor());
        proxy.sayHello("libai");
        System.out.println("#####null#####");
        proxy.sayHello(null);
    }
}
