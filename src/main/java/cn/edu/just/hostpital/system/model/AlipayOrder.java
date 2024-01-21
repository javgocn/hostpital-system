package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付宝支付订单表
 * </p>
 *
 * @author javgo
 * @since 2024-01-13
 */
@Getter
@Setter
@TableName("alipay_order")
@ApiModel(value = "AlipayOrder对象", description = "支付宝支付订单表")
public class AlipayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单ID")
    private String orderId;

    @ApiModelProperty("订单标题/商品标题/交易标题")
    private String subject;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("交易状态，见 TradeStatusType")
    private Integer tradeStatus;

    @ApiModelProperty("商户订单号")
    private String outTradeNo;

    @ApiModelProperty("支付方式，见 PayMethod")
    private Integer payMethod;

    @ApiModelProperty("产品码")
    private String productCode;

    @ApiModelProperty("产品名称")
    private String productName;

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
