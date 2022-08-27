package top.b0x0.demo.sc.hr.web;

import top.b0x0.demo.sc.hr.service.RibbonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuliling Created By 2022-08-26 22:51
 **/
@RestController
public class RibbonController {

    @Resource
    private RibbonService ribbonService;

    @RequestMapping(path = "/api/echoWord", method = RequestMethod.GET)
    public String echoWord(@RequestParam String userId) {
        return ribbonService.echoWord(userId) + " From Ribbon";
    }
    
}
