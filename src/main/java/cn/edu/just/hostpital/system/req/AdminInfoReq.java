package cn.edu.just.hostpital.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 管理员信息请求类
 *
 * @author javgo.cn
 * @date 2024/1/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminInfoReq {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("原密码")
    private String password;

    @ApiModelProperty("新密码")
    private String newPassword;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
