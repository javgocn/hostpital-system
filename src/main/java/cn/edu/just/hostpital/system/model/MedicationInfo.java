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
 * 药品信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("medication_info")
@ApiModel(value = "MedicationInfo对象", description = "药品信息表")
public class MedicationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("药品id")
    @TableId(value = "id", type = IdType.AUTO)
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
    private Byte status;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
