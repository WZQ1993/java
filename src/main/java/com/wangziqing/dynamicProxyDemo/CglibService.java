package com.wangziqing.dynamicProxyDemo;

import java.util.logging.Logger;

/**
 * 使用CGLIB字节码增强实现动态代理
 * Created by ziqingwang on 2016/12/17.
 */
public class CglibService {
    private void service(){
        Logger.getGlobal().info("CglibService is service!");
    }
}
