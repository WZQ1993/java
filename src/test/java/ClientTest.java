import com.wangziqing.nioDemo.NioClient;
import com.wangziqing.nioDemo.NioServer;
import org.junit.Test;

/**
 * Created by ziqingwang on 2016/11/17.
 */
public class ClientTest {
    @Test
    public void clientTest() throws Exception {
        for(int i=0;i<5;i++){
            NioClient client=new NioClient();
            client.sendMsg("消息");
            client.close();
        }
    }
}
