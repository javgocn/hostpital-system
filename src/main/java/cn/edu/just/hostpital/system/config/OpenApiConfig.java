package cn.edu.just.hostpital.system.config;

import cn.edu.just.hostpital.system.common.ResponseStatus;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger For OpenApi（Knif4j 访问地址：http://localhost:8080/doc.html）
 *
 * @author javgo.cn
 * @date 2024/1/20
 */
@Configuration
@EnableOpenApi // 启用 Swagger 3.0 版本的 API 文档生成功能
public class OpenApiConfig {

    /**
     * Knife4j 扩展类
     */
    private final OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API 文档分组名称, 用于区分多个分组（默认为 default）
     */
    private final String GROUP_NAME = "DEV GROUP";

    @Autowired
    public OpenApiConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket openApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName(GROUP_NAME) // API 文档分组名称, 用于区分多个分组（默认为 default）
                .apiInfo(apiInfo()) // 设置 API 文档的基本信息，这些信息会显示在文档页面上
                .select() // 配置扫描接口的路径和选择器
                .apis(RequestHandlerSelectors.basePackage("cn.edu.just.hostpital.system.controller")) // 指定 Swagger 扫描的包路径，仅生成这个包下面类的 API 文档
                .paths(PathSelectors.any()) // 指定路径选择器，这里是选择任何路径
                .build()
                .globalRequestParameters(getGlobalRequestParameters()) // 全局请求参数
                .globalResponses(HttpMethod.GET, getGlobalResponse()) // 全局响应配置
                .extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME)) // Knife4j 扩展插件
                .extensions(openApiExtensionResolver.buildSettingExtensions()); // Knife4j 全局参数配置
    }

    /**
     * 定义 API 文档的汇总信息，如文档标题、描述、联系人信息、版本号等
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API") // 文档标题
                .description("DEV Swagger API 3.0") // 文档描述
                .contact(new Contact("javgo", "https://javgo.cn", "javgocn@gmail.com")) // 联系人信息
                .termsOfServiceUrl("https://javgo.cn") // 网站地址
                .version("1.0") // 版本号
                .build();
    }

    /**
     * 定义全局请求参数，全局请求参数将会添加到所有 API 接口中
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder() // 参数配置
                .name("AppKey") // 参数名
                .description("AppKey") // 描述信息
                .in(ParameterType.QUERY) // 参数位置，在此为查询参数
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))) // 设置参数的数据模型，这里是简单的字符串类型
                .required(false) // 是否必填
                .build());
        return parameters;
    }

    /**
     * 定义全局响应信息，对所有的 GET 请求，都会添加这里定义的响应状态码和描述
     */
    private List<Response> getGlobalResponse() {
        return ResponseStatus.HTTP_STATUS_ALL.stream() // 获取所有预定义的 HTTP 响应状态码及其描述
                .map(httpStatus -> new ResponseBuilder() // 响应配置
                        .code(httpStatus.getResponseCode()) // 响应状态码
                        .description(httpStatus.getDescription()) // 响应状态描述
                        .build())
                .collect(Collectors.toList());
    }
}
