package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 科室信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@ApiModel(value = "Department对象", description = "科室信息表")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("科室id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("科室名称")
    private String name;

    @ApiModelProperty("科室描述")
    private String description;

    @ApiModelProperty("科室状态：0 启用，1 禁用，-1 已删除")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
