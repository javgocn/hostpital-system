package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 公告信息 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    @ApiModelProperty("公告id")
    @NotNull(message = "公告 id 不能为空", groups = {Delete.class})
    private Integer id;

    @ApiModelProperty("公告主题")
    @NotBlank(message = "公告主题不能为空", groups = {Save.class})
    private String subject;

    @ApiModelProperty("发起人")
    private String author;

    @ApiModelProperty("发起人id")
    @NotNull(message = "发起人 id 不能为空", groups = {Save.class})
    private Integer authorId;

    @ApiModelProperty("公告内容")
    @NotBlank(message = "公告内容不能为空", groups = {Save.class})
    private String content;

    @ApiModelProperty("公告状态：0 未发布，1 已发布, -1 已删除")
    private Integer status;

    @ApiModelProperty("发布时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}
