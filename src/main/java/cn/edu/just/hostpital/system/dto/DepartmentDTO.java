package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.common.CustomDateSerializer;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.injector.methods.Delete;
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
 * 科室信息 DTO
 *
 * @author javgo.cn
 * @date 2024/2/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @ApiModelProperty("科室id")
    @NotNull(message = "科室 id 不能为空", groups = {Update.class, Delete.class})
    private Integer id;

    @ApiModelProperty("科室名称")
    @NotBlank(message = "科室名称不能为空", groups = {Save.class})
    private String name;

    @ApiModelProperty("科室描述")
    private String description;

    @ApiModelProperty("科室状态：0 启用，1 禁用，-1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
}
