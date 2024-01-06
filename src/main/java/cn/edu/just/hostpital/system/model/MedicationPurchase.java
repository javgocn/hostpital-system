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
 * 药品采购表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("medication_purchase")
@ApiModel(value = "MedicationPurchase对象", description = "药品采购表")
public class MedicationPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("采购单id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("采购单编号")
    private String purchaseCode;

    @ApiModelProperty("总费用")
    private BigDecimal totalCost;

    @ApiModelProperty("采购日期")
    private Date purchaseDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
