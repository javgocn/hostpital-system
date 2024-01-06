package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 病历信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("medical_record")
@ApiModel(value = "MedicalRecord对象", description = "病历信息表")
public class MedicalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("病历id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("病历日期")
    private Date recordDate;

    @ApiModelProperty("患者id")
    private Integer patientId;

    @ApiModelProperty("医生id")
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

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
