package cn.edu.just.hostpital.system.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 支付宝订单 DTO
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlipayOrderDTO {

    @TableId(value = "id")
    private Long id;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("订单标题/商品标题/交易标题")
    private String subject;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("交易状态")
    private String tradeStatus;

    @ApiModelProperty("支付宝交易号")
    private String tradeNo;

    @ApiModelProperty("买家支付宝账号")
    private String buyerId;

    @ApiModelProperty("交易付款时间")
    private Timestamp gmtPayment;

    @ApiModelProperty("用户在交易中支付的金额")
    private BigDecimal buyerPayAmount;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;
}
