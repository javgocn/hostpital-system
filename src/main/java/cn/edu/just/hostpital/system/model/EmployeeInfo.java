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
 * 员工信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("employee_info")
@ApiModel(value = "EmployeeInfo对象", description = "员工信息表")
public class EmployeeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工名字")
    private String name;

    @ApiModelProperty("员工工号")
    private String jobNumber;

    @ApiModelProperty("部门id")
    private Integer departmentId;

    @ApiModelProperty("职位id")
    private Integer positionId;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别：0-女性，1-男性")
    private Byte sex;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("启用状态：0-启用，1-禁用")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}