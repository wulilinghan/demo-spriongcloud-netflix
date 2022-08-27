package top.b0x0.demo.sc.hf.web;

import top.b0x0.demo.sc.hf.service.HelloWordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@RestController
public class FeignController {

    @Resource
    private HelloWordService ribbonService;

    @RequestMapping(path = "/api/echoWord", method = RequestMethod.GET)
    public String echoWord(@RequestParam String userId) {
        return ribbonService.echoWord(userId) + " From Feign";
    }


}
