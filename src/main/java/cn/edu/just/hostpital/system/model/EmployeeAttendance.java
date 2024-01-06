package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工考勤表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("employee_attendance")
@ApiModel(value = "EmployeeAttendance对象", description = "员工考勤表")
public class EmployeeAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("考勤id")
    @TableId(value = "attendance_id", type = IdType.AUTO)
    private Integer attendanceId;

    @ApiModelProperty("员工id")
    private Integer employeeId;

    @ApiModelProperty("考勤日期")
    private Date date;

    @ApiModelProperty("考勤状态：0-正常，1-迟到，2-早退，3-旷工，4-请假，5-加班，6-出差，7-外勤，8-休息，9-节假日，10-调休，11-其他，12-已删除")
    private Byte status;

    @ApiModelProperty("备注")
    private String remark;
}
