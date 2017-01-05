package com.wangziqing.DynamicProxyDemoTest;

import com.wangziqing.dynamicProxyDemo.Serveice;
import com.wangziqing.dynamicProxyDemo.ServiceImp;
import com.wangziqing.dynamicProxyDemo.ServiceInvocationHandle;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by ziqingwang on 2016/12/17.
 */
public class DynamicProxyTest {
    @Test
    public void proxyTest() throws Exception {
        Serveice realServiceImp=new ServiceImp();
        Serveice serviceImpProxy= (Serveice) Proxy.newProxyInstance(
                Serveice.class.getClassLoader(),
                new Class[]{Serveice.class},
                new ServiceInvocationHandle(realServiceImp)
        );
        serviceImpProxy.service();
    }
}
