package com.wangziqing.nsqDemo;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQMessage;
import com.github.brainlag.nsq.NSQProducer;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.exceptions.NSQException;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Created by 王梓青 on 2016/11/20.
 */
public class NsqServices {
    private NSQProducer producer ;
    private NSQLookup lookup ;
    private ExecutorService threadPoolExecutor= Executors.newCachedThreadPool();
    public  NsqServices(String IP){
        this.lookup = new DefaultNSQLookup();
        lookup.addLookupAddress(IP,4161);
        producer=new NSQProducer().addAddress(IP, 4150).start();
    }
    public void producer(String msg,String topic){
        if(Objects.nonNull(producer)){
            try{
                producer.produce(topic, msg.getBytes());
            }catch (NSQException | TimeoutException e){
                Logger.getGlobal().info("produce messages error");
            }
        }else{
            Logger.getGlobal().info("producer is null");
        }
    }
    public void registerConsumer(String topic , String channel , final Consumer<NSQMessage> callback){
        NSQConsumer consumer = new NSQConsumer(lookup, topic, channel, new NSQMessageCallback() {
            @Override
            public void message(NSQMessage message) {
                callback.accept(message);
            }
        });
//        consumer.setExecutor(threadPoolExecutor);
        consumer.start();
    }
    public NSQProducer getProducer() {
        return producer;
    }

    public void setProducer(NSQProducer producer) {
        this.producer = producer;
    }
}
