package top.b0x0.demo.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerNode1Application {

    public static void main(String[] args) {
//        HostUtils.updateHost("localhost","sc-eureka-server-node1");
//        HostUtils.updateHost("localhost","sc-eureka-server-node2");
//        HostUtils.updateHost("localhost","sc-eureka-server-node3");
        SpringApplication.run(EurekaServerNode1Application.class, args);
    }

}
