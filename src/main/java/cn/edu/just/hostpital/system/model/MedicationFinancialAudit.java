package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 药品财务审核表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("medication_financial_audit")
@ApiModel(value = "MedicationFinancialAudit对象", description = "药品财务审核表")
public class MedicationFinancialAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("审核id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("审核编号,与采购编号一致")
    private String auditCode;

    @ApiModelProperty("采购编号")
    private Integer purchaseId;

    @ApiModelProperty("审核状态：0 待审核，1 已审核，2 审核不通过，-1 已删除")
    private Integer auditStatus;

    @ApiModelProperty("审核日期")
    private Date auditDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
