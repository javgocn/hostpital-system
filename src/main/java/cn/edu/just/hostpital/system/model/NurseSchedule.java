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
 * @since 2024-01-07
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

    @ApiModelProperty("排班表状态：0 正常，1 迟到，2 早退，3 旷工，4 请假，5 加班，6 出差，7 外勤，8 休息，9 节假日，10 调休，11 其他，-1 已删除")
    private Byte status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;
}
