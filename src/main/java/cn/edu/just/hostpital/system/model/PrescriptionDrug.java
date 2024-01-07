package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 处方药品关联表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("prescription_drug")
@ApiModel(value = "PrescriptionDrug对象", description = "处方药品关联表")
public class PrescriptionDrug implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("处方药品关联id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("处方id")
    private Integer prescriptionId;

    @ApiModelProperty("药品id")
    private Integer medicationId;

    @ApiModelProperty("药品名称")
    private String medicationName;

    @ApiModelProperty("生产厂家")
    private String manufacturer;

    @ApiModelProperty("该药品总价")
    private BigDecimal totalPrice;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("药品数量")
    private Integer quantity;

    @ApiModelProperty("备注")
    private String remark;
}
