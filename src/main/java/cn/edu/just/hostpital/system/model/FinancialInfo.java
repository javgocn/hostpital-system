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
 * 医院财务表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("financial_info")
@ApiModel(value = "FinancialInfo对象", description = "医院财务表")
public class FinancialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("财务id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("原始id，例如住院信息表的id,处方信息表的id,药品采购表的id等")
    private Integer originalId;

    @ApiModelProperty("类别：0 住院，1 处方，2 药品采购")
    private Integer category;

    @ApiModelProperty("类型：0 支出，1 收入")
    private Integer type;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("财务日期")
    private Date financialDate;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("财务状态：0 未审核，1 已审核, 2 审核不通过, -1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
