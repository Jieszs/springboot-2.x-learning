package com.example.demo.intercept;

import com.example.demo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

public interface Interceptor {
    public boolean before();
    public void after();
    /**
     ＊取代原有事件方法
     * @param invocation 一回调参数，可以通过它的 proceed 方法 回调原有事件
     * @return 原有事件返回对象
      *@throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object around(Invocation invocation)throws InvocationTargetException, IllegalAccessException;
    public void afterReturning();
    public void afterThrowing();
    //是否使 around 方法取代原有方法
    boolean useAround();
}
