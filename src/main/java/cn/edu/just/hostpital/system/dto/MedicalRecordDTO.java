package cn.edu.just.hostpital.system.dto;

import cn.edu.just.hostpital.system.validation.group.Delete;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 病例信息 DTO
 *
 * @author javgo.cn
 * @date 2024/2/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {

    @ApiModelProperty("病历id")
    @NotNull(message = "病历 id 不能为空", groups = {Update.class, Delete.class})
    private Integer id;

    @ApiModelProperty("病历日期")
    private Date recordDate;

    @ApiModelProperty("患者id")
    @NotNull(message = "患者 id 不能为空", groups = {Save.class})
    private Integer patientId;

    @ApiModelProperty("医生id")
    @NotNull(message = "医生 id 不能为空", groups = {Save.class})
    private Integer doctorId;

    @ApiModelProperty("体温")
    private Double temperature;

    @ApiModelProperty("脉搏")
    private Integer pulse;

    @ApiModelProperty("血压")
    private Integer bloodPressure;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("预约时间")
    private Date appointmentDate;

    @ApiModelProperty("病历状态：0 未就诊，1 已就诊, -1 已删除")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}
