package nioTest;

import com.wangziqing.nioDemo.NioClient;
import com.wangziqing.nioDemo.NioServer;
import org.junit.Test;

/**
 * Created by ziqingwang on 2016/11/17.
 */
public class ClientTest {
    @Test
    public void clientTest() throws Exception {
        for(int i=0;i<10000;i++){
            NioClient client=new NioClient();
            client.sendMsg("消息1");
            client.sendMsg("消息2");
            client.close();
        }
    }
}
