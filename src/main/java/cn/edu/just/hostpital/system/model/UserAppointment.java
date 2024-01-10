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
 * 用户预约表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("user_appointment")
@ApiModel(value = "UserAppointment对象", description = "用户预约表")
public class UserAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("就诊时间")
    private Date consultTime;

    @ApiModelProperty("预约科室号")
    private Integer deptId;

    @ApiModelProperty("医生id")
    private Integer doctorId;

    @ApiModelProperty("就诊用户id")
    private Integer userId;

    @ApiModelProperty("就诊状态：0 待就诊，1 已就诊，2 已取消，-1 已删除")
    private Integer status;

    @ApiModelProperty("预约描述")
    private String remark;

    @ApiModelProperty("预约时间")
    private Date createTime;
}
