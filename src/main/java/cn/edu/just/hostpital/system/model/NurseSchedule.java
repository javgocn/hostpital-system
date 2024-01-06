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
 * 护士排班表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("nurse_schedule")
@ApiModel(value = "NurseSchedule对象", description = "护士排班表")
public class NurseSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("排班id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("护士id")
    private Integer nurseId;

    @ApiModelProperty("科室id")
    private Integer deptId;

    @ApiModelProperty("工作日期")
    private Date workDate;

    @ApiModelProperty("工作时间")
    private String workTime;

    @ApiModelProperty("工作状态：0-上班，1-休息，2-请假，3-出差，4-外勤，5-已删除")
    private Byte status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;
}
