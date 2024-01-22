package cn.edu.just.hostpital.system.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户预约请求
 *
 * @author javgo.cn
 * @date 2024/1/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAppointmentReq {

    private Integer id;

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

    private String userName;
}
