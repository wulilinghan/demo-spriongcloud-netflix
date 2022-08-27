package top.b0x0.demo.sc.zuul.config;

import top.b0x0.demo.sc.zuul.router.RouteLocator;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author wuliling Created By 2022-08-27 02:12
 **/
@Configuration
public class ZuulConfig {

    @Resource
    private ZuulProperties zuulProperties;
    @Resource
    private ServerProperties server;

    @Bean
    public RouteLocator routeLocator() {
        return new RouteLocator(this.server.getServlet().getContextPath(), this.zuulProperties);
    }

}
