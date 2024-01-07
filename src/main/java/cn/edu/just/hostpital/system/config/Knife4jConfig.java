package cn.edu.just.hostpital.system.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 配置（注意：生产环境一定要关闭 Swagger，否则会导致安全问题，例如 API 泄露导致的恶意模拟请求）
 * Knife4j 访问地址：http://localhost:8080/doc.html
 * @author javgo.cn
 * @date 2024/1/7
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false) // 关闭默认的响应信息
                .apiInfo(apiInfo()) // 用于定义 api 文档汇总信息
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("cn.edu.just.hostpital.system.controller")) // 指定扫描的包路径
                .paths(PathSelectors.any()) // 指定路径处理 PathSelectors.any() 表示所有路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("医护管理系统 API 文档")
                .description("医护管理系统 API 文档")
                .contact(new Contact("JavGO", "http://www.javgo.cn", "javgocn@gmail.com"))
                .version("1.0")
                .build();
    }
}
