package cn.edu.just.hostpital.system.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 请求白名单
 *
 * @author javgo.cn
 * @date 2024/1/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "request.white-list")
public class RequestWhiteList {

    /**
     * 白名单列表
     */
    List<String> urls;

    /**
     * 用户登录页面
     */
    String userPage;

    /**
     * 管理员登录页面
     */
    String adminPage;

    /**
     * 医生登录页面
     */
    String doctorPage;

    /**
     * 登录页面
     */
    String loginPage;
}
