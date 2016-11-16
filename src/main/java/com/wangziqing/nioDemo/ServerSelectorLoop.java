package com.wangziqing.nioDemo;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 服务端轮询操作处理器
 *
 * @author ziqingwang
 *         Created by ziqingwang on 2016/11/16.
 */
public class ServerSelectorLoop {
    private Selector selector;
    private SelectorHandle handle;
    ServerSelectorLoop(){
        try{
            this.selector = Selector.open();
        }catch (IOException e){
            Logger.getGlobal().info("Selector 打开错误");
        }
    }
    ServerSelectorLoop(SelectorHandle handle) throws IOException{
        this.selector = Selector.open();
        this.handle=handle;
    }
    public SelectorHandle getHandle() {
        return handle;
    }

    public void setHandle(SelectorHandle handle) {
        this.handle = handle;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public void start() {
        while (true) {
            try {
                //不断轮询操作，阻塞知道有新的事件
                this.selector.select();
                Set<SelectionKey> selectKeys = this.selector.selectedKeys();
                Iterator<SelectionKey> it = selectKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    // 处理事件. 可以用多线程来处理.
                    this.dispatch(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事件处理操作
     * @param key
     */
    private void dispatch(SelectionKey key) {
        if(key.isAcceptable()){
            //有新连接到达
            handle.doAcceptAction(key);
        }else if(key.isReadable()){
            //有消息可读
            handle.doReadAction(key);
        }else if(key.isWritable()){
            handle.doWriteAction(key);
        }else if(key.isConnectable()){
            handle.doConnectAction(key);
        }
    }
}