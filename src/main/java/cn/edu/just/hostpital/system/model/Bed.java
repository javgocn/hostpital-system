package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 床位信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@ApiModel(value = "Bed对象", description = "床位信息表")
public class Bed implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("床位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("病房ID")
    private Integer roomId;

    @ApiModelProperty("床位编号, 1～4")
    private Integer bedNumber;

    @ApiModelProperty("单价")
    private BigDecimal price;

    @ApiModelProperty("占用状态:0 空闲，1 占用, 2 维修，3 预定，-1 已删除")
    private Integer status;

    @ApiModelProperty("床位备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
