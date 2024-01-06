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
 * 药品采购明细表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("medication_purchase_details")
@ApiModel(value = "MedicationPurchaseDetails对象", description = "药品采购明细表")
public class MedicationPurchaseDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("采购明细id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("采购单id")
    private Integer purchaseId;

    @ApiModelProperty("药品id")
    private Integer medicationId;

    @ApiModelProperty("采购数量")
    private Integer quantity;

    @ApiModelProperty("费用")
    private BigDecimal cost;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
