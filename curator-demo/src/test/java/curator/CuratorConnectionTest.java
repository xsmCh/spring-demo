package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.time.LocalDateTime;

public class CuratorConnectionTest {

    @Test
    public void test_connection() throws InterruptedException {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 10);
        // 构建客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.149.135:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(1 * 1000)
                .retryPolicy(new RetryPolicy() {
                    @Override
                    public boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper) {
                        System.out.println("超时：" + LocalDateTime.now());
                        return false;
                    }
                })
                .namespace("itheima")
                .build();

        // 开启连接
        client.start();

        Thread.sleep(100*1000);
        System.out.println();
    }

}
