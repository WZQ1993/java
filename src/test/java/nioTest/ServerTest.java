package nioTest;

import com.wangziqing.nioDemo.NioClient;
import com.wangziqing.nioDemo.NioServer;
import org.junit.Test;

/**
 * Created by ziqingwang on 2016/11/17.
 */
public class ServerTest {
    NioClient client;
    NioServer server;
    @Test
    public void serverStart() throws Exception {
        server=new NioServer();
        server.startServer();
    }

    @Test
    public void serverClose() throws Exception {
        client.close();
        server.close();
    }
}
