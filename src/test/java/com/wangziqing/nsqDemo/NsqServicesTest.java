package com.wangziqing.nsqDemo;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by 王梓青 on 2016/11/20.
 */
public class NsqServicesTest {
    @Test
    public void producer() throws Exception {
        NsqServices nsqServices=new NsqServices("192.168.2.104");
        nsqServices.producer("生产消息1","topic");
        nsqServices.producer("生产消息2","topic");
//        Thread.sleep(50000);
    }

    @Test
    public void registerConsumer() throws Exception {
        NsqServices nsqServices=new NsqServices("192.168.2.104");
        nsqServices.registerConsumer("topic","channel_1",nsqMessage -> {
            Logger.getGlobal().info("接收到消息："+new String(nsqMessage.getMessage()));
            //标识消息处理完成
            nsqMessage.finished();
            //标识消息重新投放
//            nsqMessage.requeue();
        });
        while(true){
            //客户端在线收到推送
        }
    }

}