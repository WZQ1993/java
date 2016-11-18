package com.wangziqing.nioDemo;

import com.sun.javafx.geom.AreaOp;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 一个简单的nio服务器示例
 * Created by ziqingwang on 2016/11/16.
 */
public class NioServer {
    private ServerSelectorLoop connectionSelector;
    private ServerSelectorLoop readSelector;
    private ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private ServerSocketChannel ssc;
    private volatile Boolean ReadRunning=false;
    private static int sum=0;
    public NioServer() {
        connectionSelector = new ServerSelectorLoop();
        readSelector = new ServerSelectorLoop();
        SelectorHandle handle = new SelectorHandle() {
            @Override
            public void doAcceptAction(SelectionKey key) {
                try {
                    //得到注册到这个selector的channel对象
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    //处理
                    SocketChannel sc = ssc.accept();
                    //将新连接注册到readSelector
                    sc.configureBlocking(false);
                    sc.register(readSelector.getSelector(), SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                    //connext单线程，无需同步
                    if (!ReadRunning) {
                        threadPool.submit(() -> {
                            readSelector.start();
                        });
                        ReadRunning = true;
                    }
                    Logger.getGlobal().info("连接总数："+sum++);
                } catch (IOException e) {
                    Logger.getGlobal().info("连接socket错误");
                }
            }

            @Override
            public void doConnectAction(SelectionKey key) {
                Logger.getGlobal().info("connect");
            }

            @Override
            public void doReadAction(SelectionKey key) {
                try {
                    //因为select操作并未对cancelledKey同步,因此有可能再selectedKey中出现的key是已经被取消的.
                    // 这一点需要注意.需要校验:key.isValid() && key.isReadable()...
                    //得到注册的socketChannel
                    if(key.isValid()&&key.isReadable()){
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer temp=(ByteBuffer) key.attachment();
                        int count = sc.read(temp);
                        if (count == -1) {
                            //连接断开
                            key.cancel();
                            sc.close();
                            return;
                        }
                        temp.flip();
                        String msg = Charset.forName("utf-8").decode(temp).toString();
                        Logger.getGlobal().info("接受消息：" + msg);
                        temp.clear();
                    }
                } catch (IOException e) {
                    Logger.getGlobal().info("读取消息错误");
                }
            }

            @Override
            public void doWriteAction(SelectionKey key) {
                Logger.getGlobal().info("write");
            }
        };
        connectionSelector.setHandle(handle);
        readSelector.setHandle(handle);
    }

    public void startServer() throws IOException {
        ssc= ServerSocketChannel.open();
        //开启非阻塞
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(7878));

        ssc.register(connectionSelector.getSelector(),SelectionKey.OP_ACCEPT);
        //启动接收轮询和读取轮询线程
//        threadPool.submit(()->{
//            connectionSelector.start();
//        });
        connectionSelector.start();
        Logger.getGlobal().info("server start!");
    }
    public void close(){
        try{
            connectionSelector.getSelector().close();
            readSelector.getSelector().close();
            ssc.close();
        }catch (IOException e){
            Logger.getGlobal().info("server close error!");
        }
    }
    public ServerSelectorLoop getReadSelector() {
        return readSelector;
    }

    public void setReadSelector(ServerSelectorLoop readSelector) {
        this.readSelector = readSelector;
    }

}
