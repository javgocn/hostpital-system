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
 * 系统资源表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("system_resources")
@ApiModel(value = "SystemResources对象", description = "系统资源表")
public class SystemResources implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "resource_id", type = IdType.AUTO)
    private Integer resourceId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源类型")
    private Byte type;

    @ApiModelProperty("资源URL")
    private String url;

    @ApiModelProperty("资源请求方式")
    private String method;

    @ApiModelProperty("父资源ID")
    private Integer parentId;

    @ApiModelProperty("启用状态：0 启用，1 禁用，-1 已删除")
    private Byte status;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
