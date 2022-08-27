package top.b0x0.demo.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerNode3Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerNode3Application.class, args);
    }

}
