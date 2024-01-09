package cn.edu.just.hostpital.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    private Integer id;

    @ApiModelProperty("账户")
    private String username;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("性别：0 女性，1 男性, 2 保密")
    private Byte sex;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
