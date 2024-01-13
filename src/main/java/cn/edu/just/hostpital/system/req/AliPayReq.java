package cn.edu.just.hostpital.system.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 支付宝支付请求参数
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AliPayReq {

    /**
     * 订单标题（不可以使用特殊字符）
     */
    private String subject;

    /**
     * 商户订单号（由商家自定义的唯一订单号）
     */
    private String outTradeNo;

    /**
     * 订单总金额(元)，最小值为0.01
     */
    private BigDecimal totalAmount;
}
