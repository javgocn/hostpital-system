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
 * 职位信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("position_info")
@ApiModel(value = "PositionInfo对象", description = "职位信息表")
public class PositionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("职位id")
    @TableId(value = "position_id", type = IdType.AUTO)
    private Integer positionId;

    @ApiModelProperty("职位名称")
    private String positionName;

    @ApiModelProperty("职位描述")
    private String description;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
