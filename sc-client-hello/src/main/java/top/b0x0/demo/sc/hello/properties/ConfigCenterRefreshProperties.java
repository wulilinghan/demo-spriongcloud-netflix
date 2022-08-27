package top.b0x0.demo.sc.hello.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author wuliling Created By 2022-08-27 15:30
 **/
@Data
@Component
@ConfigurationProperties(prefix = "data")
public class ConfigCenterRefreshProperties {

    private String env;

    private UserInfo user;

    @Data
    public static class UserInfo {
        private String username;
        private String password;
    }
}