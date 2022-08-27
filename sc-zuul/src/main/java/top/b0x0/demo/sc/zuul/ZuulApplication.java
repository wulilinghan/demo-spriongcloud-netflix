package top.b0x0.demo.sc.zuul;

import top.b0x0.demo.sc.zuul.filter.TokenFilter;
import top.b0x0.demo.sc.zuul.service.RefreshRouteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wuliling Created By 2022-08-27 02:12
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Resource
    private RefreshRouteService refreshRouteService;
    @Resource
    private ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("api/refresh")
    public String refresh() {
        refreshRouteService.refreshRoute();
        return "success";
    }

    @RequestMapping("api/queryRouteInfo")
    @ResponseBody
    public Map<String, Object> queryRouteInfo() {
        return zuulHandlerMapping.getHandlerMap();
    }

}
