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

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户信息 DTO
 *
 * @author javgo.cn
 * @date 2024/1/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    @ApiModelProperty("用户id")
    @NotNull(groups = {Update.class, Delete.class}, message = "用户id不能为空")
    private Integer id;

    @ApiModelProperty("账户")
    @NotNull(groups = {Update.class, Save.class}, message = "账户不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotNull(groups = {Update.class, Save.class}, message = "密码不能为空")
    private String password;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("用户名")
    @NotNull(groups = {Update.class, Save.class}, message = "用户名不能为空")
    private String name;

    @ApiModelProperty("性别：0 女性，1 男性, 2 保密")
    @NotNull(groups = {Save.class}, message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty("电话")
    @NotNull(groups = {Save.class}, message = "电话不能为空")
    private String telephone;

    @ApiModelProperty("邮箱")
    @NotNull(groups = {Save.class}, message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("地址")
    @NotNull(groups = {Save.class}, message = "地址不能为空")
    private String address;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}
