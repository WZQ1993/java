import com.wangziqing.nioDemo.NioClient;
import com.wangziqing.nioDemo.NioServer;
import org.junit.Test;

/**
 * Created by ziqingwang on 2016/11/17.
 */
public class NioTest {
    NioClient client;
    NioServer server;
    @Test
    public void serverTest() throws Exception {
        server=new NioServer();
        server.startServer();
    }

    @Test
    public void clientTest() throws Exception {
        client=new NioClient();
        client.sendMsg("消息");
    }

    @Test
    public void sendMsg() throws Exception {

    }

    @Test
    public void close() throws Exception {
        client.close();
        server.close();
    }
}
