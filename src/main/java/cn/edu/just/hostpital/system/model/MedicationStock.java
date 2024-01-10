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
 * 药品入库作业表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("medication_stock")
@ApiModel(value = "MedicationStock对象", description = "药品入库作业表")
public class MedicationStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入库id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("采购编号")
    private Integer purchaseId;

    @ApiModelProperty("入库数量")
    private Integer quantity;

    @ApiModelProperty("入库日期")
    private Date stockDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("入库状态：0 未审核，1 已审核, -1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
