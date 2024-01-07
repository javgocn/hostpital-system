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
 * 病房信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@ApiModel(value = "Room对象", description = "病房信息表")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("病房id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("病房编号，如 A-1-101 表示 A楼1层101房间，B-2-201 表示 B楼2层201房间")
    private String roomNumber;

    @ApiModelProperty("科室ID")
    private Integer departmentId;

    @ApiModelProperty("床位数量")
    private Integer bedCount;

    @ApiModelProperty("病房状态：0 空闲（默认），1 可用，2 维修，3 已满，-1 已删除")
    private Integer status;

    @ApiModelProperty("病房备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
