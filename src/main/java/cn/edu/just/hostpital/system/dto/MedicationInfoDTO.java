package cn.edu.just.hostpital.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 药品信息 DTO
 *
 * @author javgo.cn
 * @date 2024/2/7
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInfoDTO {

    @ApiModelProperty("药品id")
    private Integer id;

    @ApiModelProperty("药品编号")
    private String medicationCode;

    @ApiModelProperty("药品名称")
    private String medicationName;

    @ApiModelProperty("生产厂家")
    private String manufacturer;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("单位例如片、瓶、盒等")
    private String unit;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("关于药品的其他重要信息，例如用法用量、副作用等")
    private String remark;

    @ApiModelProperty("药品状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
