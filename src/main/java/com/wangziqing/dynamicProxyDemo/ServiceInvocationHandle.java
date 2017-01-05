package com.wangziqing.dynamicProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Created by ziqingwang on 2016/12/17.
 */
public class ServiceInvocationHandle implements InvocationHandler {
    private Object target;
    public ServiceInvocationHandle(Object target){
        this.target=target;
    }

    /**
     * 代理逻辑
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 代理方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        if(method.getName().equals("service")){
            Logger.getGlobal().info("Before The proxy doing the method name service");
            //执行真实对象的方法
            result= method.invoke(target,args);
            Logger.getGlobal().info("After The proxy doing the method name service");
        }
        return result;
    }
}
