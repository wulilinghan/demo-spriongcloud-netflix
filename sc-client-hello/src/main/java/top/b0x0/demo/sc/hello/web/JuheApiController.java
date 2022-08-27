package top.b0x0.demo.sc.hello.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.demo.sc.common.domain.JuheResponse;
import top.b0x0.demo.sc.hello.service.JuheApiService;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * 聚合api调用
 *
 * @author musui
 */
@RestController
@RequestMapping("juhe")
@Validated
@Api(tags = "聚合数据：第三方api调用")
public class JuheApiController {

    @Resource
    JuheApiService juheApiService;

    @GetMapping("todayOnhistory/query")
    @ApiOperation("历史上的今天")
    public JuheResponse getTodayInHistory() {
        return juheApiService.getTodayInHistory();
    }

    @GetMapping("mobile/phoneAttribution")
    @ApiOperation("手机号归属地查询")
    public JuheResponse getPhoneAttribution(@NotBlank(message = "手机号不能为空") @RequestParam("phone") String phone) {
        return juheApiService.getPhoneAttribution(phone);
    }

    @GetMapping("xzqh/query")
    @ApiOperation("行政区划查询")
    public JuheResponse getAdministrativeDivisions(String id) {
        return juheApiService.getAdministrativeDivisions(id);
    }

    @GetMapping("simpleWeather/query")
    @ApiOperation("天气查询")
    public JuheResponse getSimpleWeather(String city) {
        return juheApiService.getSimpleWeather(city);
    }

}
