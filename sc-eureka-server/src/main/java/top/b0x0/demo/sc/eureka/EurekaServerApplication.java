package top.b0x0.demo.sc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import top.b0x0.demo.sc.common.utils.HostUtils;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        // 开发环境和docker在同一局域网下，docker服务器ip是192.168.3.50 这里改下hosts映射，不一定能修改成功最终可能还需要手动改下
        HostUtils.updateHost("192.168.3.50","eureka-node01");
        HostUtils.updateHost("192.168.3.50","eureka-node02");
        HostUtils.updateHost("192.168.3.50","eureka-node03");
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
