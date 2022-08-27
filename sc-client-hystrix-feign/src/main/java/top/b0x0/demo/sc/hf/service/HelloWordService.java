package top.b0x0.demo.sc.hf.service;

import top.b0x0.demo.sc.hf.service.hystrix.HelloWordServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@FeignClient(value = "sc-helloword-client", fallback = HelloWordServiceHystrix.class)
public interface HelloWordService {

    @RequestMapping(value = "/api/echoWord", method = RequestMethod.GET)
    String echoWord(@RequestParam String userId);

}
