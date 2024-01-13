package cn.edu.just.hostpital.system.config;

import cn.edu.just.hostpital.system.enums.CharsetType;
import cn.edu.just.hostpital.system.enums.FormatType;
import cn.edu.just.hostpital.system.enums.SignType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * alipay 属性配置类
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    /**
     * Alipay 分配给开发者的应用ID
     */
    private String appId;

    /**
     * 支付宝公钥（支付宝生成，用于验签）
     */
    private String alipayPublicKey;

    /**
     * 应用私钥（开发者生成，用于签名）
     */
    private String appPrivateKey;

    /**
     * 支付宝网关
     */
    private String gatewayUrl;

    /**
     * 支付宝同步通知地址
     */
    private String returnUrl;

    /**
     * 支付宝异步通知地址
     */
    private String notifyUrl;

    /**
     * 支付宝返回数据格式
     */
    private String format = FormatType.JSON.getFormat();

    /**
     * 字符编码格式
     */
    private String charset = CharsetType.UTF_8.getCharset();

    /**
     * 签名算法类型
     */
    private String signType = SignType.RSA2.getType();
}
