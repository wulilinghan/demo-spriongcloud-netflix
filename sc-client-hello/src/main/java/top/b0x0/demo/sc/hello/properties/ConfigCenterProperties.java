package top.b0x0.demo.sc.hello.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wuliling Created By 2022-08-27 15:30
 **/
@Data
@Component
public class ConfigCenterProperties {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

}