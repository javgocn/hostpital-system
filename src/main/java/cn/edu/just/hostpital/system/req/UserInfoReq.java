package cn.edu.just.hostpital.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息请求
 *
 * @author javgo.cn
 * @date 2024/1/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoReq {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("账户")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("性别：0 女性，1 男性, 2 保密")
    private Byte sex;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("地址")
    private String address;
}
