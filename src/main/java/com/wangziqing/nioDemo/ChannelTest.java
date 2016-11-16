package com.wangziqing.nioDemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

/**
 * FileChannel 从文件中读写数据。
 * DatagramChannel 能通过UDP读写网络中的数据。
 * SocketChannel 能通过TCP读写网络中的数据。
 * ServerSocketChannel可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
 * Created by ziqingwang on 2016/11/15.
 */
public class ChannelTest {
    /**
     * Channel和Buffer的基本使用
     */
    private void test(){
        //使用java7 的try-with-resourse，File关闭的时候会关闭channel，见jdk
        try(FileChannel inFileChannel=new RandomAccessFile("test.txt","rw").getChannel()){
            ByteBuffer charBuffer=ByteBuffer.allocate(48);
            //channel将数据写到charBuffer
            int canRead=inFileChannel.read(charBuffer);
            while(canRead!=-1){
                //切换到读模式
                charBuffer.flip();
                while(charBuffer.hasRemaining()){
                    Logger.getGlobal().info("read :"+charBuffer.get());
                }
                //清除缓冲区
                charBuffer.clear();
                canRead=inFileChannel.read(charBuffer);
            }
        }catch (IOException e){
            Logger.getGlobal().info("error mag="+e.getMessage());
        }
    }

}
