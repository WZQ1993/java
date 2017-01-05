package com.wangziqing.dynamicProxyDemo;

import java.util.logging.Logger;

/**
 *
 * Created by ziqingwang on 2016/12/17.
 */
public class ServiceImp implements Serveice {
    @Override
    public void service() {
        Logger.getGlobal().info("serviceImp do service.");
    }
}
