package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 医生信息请求类 DTO
 *
 * @author javgo.cn
 * @date 2024/1/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfoDTO {

    @ApiModelProperty("医生id")
    @NotNull(groups = {Update.class, Delete.class}, message = "医生id不能为空")
    private Integer id;

    @ApiModelProperty("医生用户名")
    @NotNull(groups = {Update.class, Save.class}, message = "医生用户名不能为空")
    private String username;

    @ApiModelProperty("医生密码")
    @NotNull(groups = {Save.class}, message = "医生密码不能为空")
    private String password;

    @ApiModelProperty("医生名字")
    @NotNull(groups = {Update.class, Save.class}, message = "医生名字不能为空")
    private String name;

    @ApiModelProperty("医生工号")
    private String jobNumber;

    @ApiModelProperty("部门id")
    @NotNull(groups = {Save.class}, message = "部门id不能为空")
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    @ApiModelProperty("职位id")
    @NotNull(groups = {Save.class}, message = "职位id不能为空")
    private Integer positionId;

    /**
     * 职位名称
     */
    private String positionName;

    @ApiModelProperty("电话")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", groups = {Save.class, Update.class}, message = "手机号码格式不正确")
    @NotNull(groups = {Save.class, Update.class}, message = "电话不能为空")
    private String telephone;

    @ApiModelProperty("邮箱")
    @Email(groups = {Save.class, Update.class}, message = "邮箱格式不正确")
    @NotNull(groups = {Save.class, Update.class}, message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("性别：0 女性，1 男性，2 保密")
    @NotNull(groups = {Save.class, Update.class}, message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty("生日")
    @NotNull(groups = {Save.class, Update.class}, message = "生日不能为空")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date birthday;

    @ApiModelProperty("住址")
    @NotNull(groups = {Save.class}, message = "住址不能为空")
    private String address;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}
