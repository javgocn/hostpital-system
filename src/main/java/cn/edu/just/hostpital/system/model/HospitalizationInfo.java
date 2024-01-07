package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 住院信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("hospitalization_info")
@ApiModel(value = "HospitalizationInfo对象", description = "住院信息表")
public class HospitalizationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("住院id号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("患者id")
    private Integer patientId;

    @ApiModelProperty("患者姓名")
    private String patientName;

    @ApiModelProperty("病房ID")
    private Integer roomId;

    @ApiModelProperty("床位号")
    private Integer bedId;

    @ApiModelProperty("入院日期")
    private Date admissionDate;

    @ApiModelProperty("出院日期")
    private Date dischargeDate;

    @ApiModelProperty("总费用")
    private BigDecimal totalCost;

    @ApiModelProperty("住院状态：0 住院中，1 已出院, -1 已删除")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
