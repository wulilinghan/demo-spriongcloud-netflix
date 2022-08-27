package top.b0x0.demo.sc.hello.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import top.b0x0.demo.sc.hello.properties.ConfigCenterRefreshProperties;
import top.b0x0.demo.sc.hello.properties.ConfigCenterProperties;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@RefreshScope
@RestController
@RequestMapping("api")
public class HelloWordClientController {

    @Value("${server.port}")
    private int port;
    @Resource
    ConfigCenterProperties properties;

    @Resource
    ConfigCenterRefreshProperties refreshProperties;

    @GetMapping(path = "/echoWord")
    public String echoWord(@RequestParam String userId) {
        return "Hi Hello Word ! @[" + userId + "] from HelloWordClient port: " + port + ", CurTime: " + LocalDateTime.now();
    }

    @GetMapping(path = "/staticShow")
    public String staticShow() {
        return "CurTime: " + LocalDateTime.now() + "\r\n" + JSON.toJSONString(properties);
    }

    @GetMapping(path = "/refresh")
    public String refresh() {
        return "CurTime: " + LocalDateTime.now() + "\r\n" + JSON.toJSONString(refreshProperties);
    }
}
