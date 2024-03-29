package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
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
 * 管理员信息 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfoDTO {

    @ApiModelProperty("用户id")
    @NotNull(groups = {Update.class, Delete.class}, message = "用户id不能为空")
    private Integer id;

    @ApiModelProperty("用户名")
    @NotNull(groups = {Save.class, Update.class}, message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotNull(groups = {Save.class}, message = "密码不能为空")
    private String password;

    @ApiModelProperty("姓名")
    @NotNull(groups = {Save.class, Update.class}, message = "姓名不能为空")
    private String name;

    @ApiModelProperty("电话")
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", groups = {Save.class, Update.class}, message = "电话格式不正确")
    @NotNull(groups = {Save.class, Update.class}, message = "电话不能为空")
    private String tel;

    @ApiModelProperty("邮箱")
    @Email(groups = {Save.class, Update.class}, message = "邮箱格式不正确")
    @NotNull(groups = {Save.class, Update.class}, message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}