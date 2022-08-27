package top.b0x0.demo.sc.hf.service.hystrix;

import top.b0x0.demo.sc.hf.service.HelloWordService;
import org.springframework.stereotype.Component;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@Component
public class HelloWordServiceHystrix implements HelloWordService {

    @Override
    public String echoWord(String userId) {
        return "echoWord by userId：" + userId + " err！from feign hystrix";
    }

}
