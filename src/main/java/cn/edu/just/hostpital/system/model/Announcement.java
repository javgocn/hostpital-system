package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@ApiModel(value = "Announcement对象", description = "公告表")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("公告主题")
    private String subject;

    @ApiModelProperty("发起人")
    private String author;

    @ApiModelProperty("发起人id")
    private Integer authorId;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("公告状态：0 未发布，1 已发布, -1 已删除")
    private Integer status;

    @ApiModelProperty("发布时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
