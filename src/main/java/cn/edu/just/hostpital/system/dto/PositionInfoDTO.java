package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 职位信息请求类 DTO
 *
 * @author javgo.cn
 * @date 2024/2/8
 */
public class PositionInfoDTO {

    @ApiModelProperty("职位id")
    private Integer positionId;

    @ApiModelProperty("职位名称")
    private String positionName;

    @ApiModelProperty("职位描述")
    private String description;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}
