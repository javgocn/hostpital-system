package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 住院信息 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
public class HospitalizationInfoDTO {

    @ApiModelProperty("住院id号")
    @NotNull(message = "住院 id 号不能为空", groups = {Update.class, Delete.class})
    private Integer id;

    @ApiModelProperty("患者id")
    private Integer patientId;

    @ApiModelProperty("患者姓名")
    @NotBlank(message = "患者姓名不能为空", groups = {Save.class})
    private String patientName;

    @ApiModelProperty("病房ID")
    @NotNull(message = "病房 id 不能为空", groups = {Save.class})
    private Integer roomId;

    @ApiModelProperty("床位号")
    @NotNull(message = "床位 id 不能为空", groups = {Save.class})
    private Integer bedId;

    @ApiModelProperty("入院日期")
    @NotNull(message = "入院日期不能为空", groups = {Save.class})
    private Date admissionDate;

    @ApiModelProperty("出院日期")
    @NotNull(message = "出院日期不能为空", groups = {Update.class})
    private Date dischargeDate;

    @ApiModelProperty("总费用")
    private BigDecimal totalCost;

    @ApiModelProperty("住院状态：0 住院中，1 已出院, -1 已删除")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
