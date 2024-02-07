package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Select;
import cn.edu.just.hostpital.system.validation.group.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户预约表 DTO
 *
 * @author javgo.cn
 * @date 2024/1/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAppointmentDTO {

    @NotNull(groups = {Update.class, Delete.class}, message = "预约id不能为空")
    private Integer id;

    @ApiModelProperty("就诊时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date consultTime;

    @ApiModelProperty("预约科室号")
    @NotNull(groups = {Save.class}, message = "预约科室号不能为空")
    private Integer deptId;

    @ApiModelProperty("医生id")
    @NotNull(groups = {Save.class}, message = "医生id不能为空")
    private Integer doctorId;

    @ApiModelProperty("就诊用户id")
    @NotNull(groups = {Save.class}, message = "就诊用户id不能为空")
    private Integer userId;

    /**
     * 用户名：冗余字段，用于查询
     */
    @NotNull(groups = {Select.class}, message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("就诊状态：0 待就诊，1 已就诊，2 已取消，-1 已删除")
    private Integer status;

    @ApiModelProperty("预约描述")
    private String remark;

    @ApiModelProperty("预约时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
}
