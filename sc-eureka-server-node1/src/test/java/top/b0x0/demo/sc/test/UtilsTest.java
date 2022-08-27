package top.b0x0.demo.sc.test;

import org.junit.Test;
import top.b0x0.demo.sc.common.utils.HostUtils;

/**
 * @author wuliling Created By 2022-08-27 0:34
 **/
public class UtilsTest extends AppTest {

    @Test
    public void test_updateHost() {
        HostUtils.updateHost("localhost", "sc-eureka-server-node1");
        HostUtils.updateHost("localhost", "sc-eureka-server-node2");
        HostUtils.updateHost("localhost", "sc-eureka-server-node3");
    }
}
