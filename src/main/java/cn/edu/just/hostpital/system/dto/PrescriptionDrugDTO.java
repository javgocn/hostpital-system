package cn.edu.just.hostpital.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 处方药品关联 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDrugDTO {

    @ApiModelProperty("处方药品关联id")
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
