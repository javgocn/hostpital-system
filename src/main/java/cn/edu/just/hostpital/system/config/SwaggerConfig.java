package cn.edu.just.hostpital.system.config;

import cn.edu.just.hostpital.system.common.ResponseStatus;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger 配置 (访问地址：http://localhost:8080/swagger-ui/index.html)
 * @author javgo.cn
 * @date 2024/1/20
 */
// @Configuration
// @EnableOpenApi // 启用 Swagger 3.0 版本的 API 文档生成功能
public class SwaggerConfig {

    // @Bean
    public Docket openApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DEV GROUP") // API 文档分组名称, 用于区分多个分组（默认为 default）
                .apiInfo(apiInfo()) // 设置 API 文档的基本信息，这些信息会显示在文档页面上
                .select() // 配置扫描接口的路径和选择器
                .apis(RequestHandlerSelectors.basePackage("cn.edu.just.hostpital.system.controller")) // 指定 Swagger 扫描的包路径，仅生成这个包下面类的 API 文档
                .paths(PathSelectors.any()) // 指定路径选择器，这里是选择任何路径
                .build()
                .globalRequestParameters(getGlobalRequestParameters()) // 全局请求参数
                .globalResponses(HttpMethod.GET, getGlobalResponse()); // 全局响应配置
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

    /**
     * 该 Bean 用于处理 springfox 相关的配置
     * @return BeanPostProcessor
     */
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor(){
        return new BeanPostProcessor() {
            // 在所有 Bean 初始化之前，执行该方法
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // 如果该 Bean 是 WebMvcRequestHandlerProvider 或 WebFluxRequestHandlerProvider 类型
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    // 获取 bean 中的 HandlerMappings 并进行自定义
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            // 用于自定义 Springfox 的 HandlerMappings
            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings){
                // 复制一份 mappings,过滤出所有 mapping 为 null 的元素
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getHandlerMethods().size() > 0)
                        .collect(Collectors.toList());

                // 清空 mappings
                mappings.clear();
                // 添加 copy
                mappings.addAll(copy);
            }

            // 用于获取 bean 中的 HandlerMappings
            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean){
                try{
                    // 通过反射获取 bean 中的 handlerMappings 属性
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    // 设置可访问(因为该属性是 private 的)
                    assert field != null;
                    field.setAccessible(true);
                    // 将获取到的属性值转为 List<RequestMappingInfoHandlerMapping> 类型
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
