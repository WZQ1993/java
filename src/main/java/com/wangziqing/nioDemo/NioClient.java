package com.wangziqing.nioDemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

import static java.nio.ByteBuffer.wrap;

/**
 * Created by ziqingwang on 2016/11/16.
 */
public class NioClient {
    private SocketChannel socketChannel;
    public  NioClient(){
        try{
            socketChannel=SocketChannel.open();
            boolean isConnect=socketChannel.connect(new InetSocketAddress("localhost",7878));
            if(isConnect){
                Logger.getGlobal().info("客户端连接成功");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendMsg(String msg){
        try{
            ByteBuffer temp=ByteBuffer.wrap(msg.getBytes("utf-8"));
            while(temp.hasRemaining()){
                socketChannel.write(temp);
            }
        }catch (UnsupportedEncodingException e){
            Logger.getGlobal().info("发送失败,编码错误");
        }catch (IOException e){
            Logger.getGlobal().info("发送失败,IO错误");
        }
    }

    public void close(){
        try{
            socketChannel.close();
        }catch (IOException e){
            Logger.getGlobal().info("close error");
        }
    }
}
