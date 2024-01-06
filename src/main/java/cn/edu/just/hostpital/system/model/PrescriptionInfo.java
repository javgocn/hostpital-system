package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 处方信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("prescription_info")
@ApiModel(value = "PrescriptionInfo对象", description = "处方信息表")
public class PrescriptionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("处方id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("开方日期")
    private Date createDate;

    @ApiModelProperty("患者姓名")
    private String username;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("医生id")
    private Integer doctorId;

    @ApiModelProperty("医生姓名")
    private String doctorName;

    @ApiModelProperty("总费用")
    private BigDecimal totalPrice;

    @ApiModelProperty("支付状态：0-待支付,1-已支付")
    private Integer paymentStatus;

    @ApiModelProperty("处方备注")
    private String remark;
}
