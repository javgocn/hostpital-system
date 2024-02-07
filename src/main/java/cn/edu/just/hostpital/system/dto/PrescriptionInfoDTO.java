package cn.edu.just.hostpital.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 处方 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionInfoDTO {

    @ApiModelProperty("处方id")
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

    @ApiModelProperty("支付状态：0 待支付, 1 已支付，2 已退款")
    private Integer paymentStatus;

    @ApiModelProperty("处方备注")
    private String remark;

    @ApiModelProperty("处方状态：0 未审核，1 已审核,2 审核不通过, -1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
