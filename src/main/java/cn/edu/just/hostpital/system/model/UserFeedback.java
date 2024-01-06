package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户反馈表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("user_feedback")
@ApiModel(value = "UserFeedback对象", description = "用户反馈表")
public class UserFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("反馈id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("反馈主题")
    private String subject;

    @ApiModelProperty("反馈人")
    private String userName;

    @ApiModelProperty("反馈状态：0-待回复，1-已回复, -1-已删除")
    private Integer status;

    @ApiModelProperty("反馈内容")
    private String feedback;

    @ApiModelProperty("回复内容")
    private String reply;

    @ApiModelProperty("回复时间")
    private Timestamp replyTime;

    @ApiModelProperty("回复人")
    private String replyUser;

    @ApiModelProperty("回复人id")
    private Integer replyUserId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;
}
