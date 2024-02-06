package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户反馈 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedbackDTO {

    @ApiModelProperty("反馈id")
    @NotNull(message = "用户反馈 id 不能为空", groups = {Update.class, Delete.class})
    private Integer id;

    @ApiModelProperty("用户id")
    @NotNull(message = "用户 id 不能为空", groups = {Save.class})
    private Integer userId;

    @ApiModelProperty("反馈主题")
    @NotBlank(message = "反馈主题不能为空", groups = {Save.class})
    private String subject;

    @ApiModelProperty("反馈人")
    @NotBlank(message = "反馈人不能为空", groups = {Save.class})
    private String userName;

    @ApiModelProperty("反馈状态：0 待回复，1 已回复, -1 已删除")
    private Integer status;

    @ApiModelProperty("反馈内容")
    @NotBlank(message = "反馈内容不能为空", groups = {Save.class})
    private String feedback;

    @ApiModelProperty("回复内容")
    @NotBlank(message = "回复内容不能为空", groups = {Update.class})
    private String reply;

    @ApiModelProperty("回复时间")
    private Timestamp replyTime;

    @ApiModelProperty("回复人")
    @NotBlank(message = "回复人不能为空", groups = {Update.class})
    private String replyUser;

    @ApiModelProperty("回复人id")
    @NotNull(message = "回复人 id 不能为空", groups = {Update.class})
    private Integer replyUserId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
