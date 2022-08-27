package top.b0x0.demo.sc.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 只在dev和test,native,local环境下开启
 *
 * @author TANG
 */
@Configuration
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class) //knife4j需要引入
@Profile({"dev", "test", "native", "local"})
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描controller路径
                .apis(RequestHandlerSelectors.basePackage("top.b0x0.demo.springcloud.web"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * name:开发者姓名
     * url:开发者网址
     * email:开发者邮箱
     *
     * @return /
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("demo-springcloud")
                // 文档接口的描述
                .description("demo-springcloud接口文档")
                .contact(new Contact("TANG", "wulilinghan.github.io", "1902325071@qq.com"))
                // 版本号
                .version("1.0.0")
                .build();
    }

}