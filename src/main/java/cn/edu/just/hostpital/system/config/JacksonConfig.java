package cn.edu.just.hostpital.system.config;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Date;

/**
 * 自定义序列化器
 *
 * @author javgo.cn
 * @date 2024/2/8
 */
@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 创建模块并注册自定义日期序列化器
        SimpleModule module = new SimpleModule();
        module.addSerializer(Date.class, new CustomDateSerializer());
        // 注册模块到 ObjectMapper
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
