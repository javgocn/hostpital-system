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
 * 护士信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("nurse_info")
@ApiModel(value = "NurseInfo对象", description = "护士信息表")
public class NurseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("护士id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("护士用户名")
    private String username;

    @ApiModelProperty("护士密码")
    private String password;

    @ApiModelProperty("护士名字")
    private String name;

    @ApiModelProperty("护士工号")
    private String jobNumber;

    @ApiModelProperty("部门id")
    private Integer departmentId;

    @ApiModelProperty("职位id")
    private Integer positionId;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别，0 女性，1 男性， 2 保密")
    private Integer sex;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
