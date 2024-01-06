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
 * 患者家属信息表
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Getter
@Setter
@TableName("patient_relative")
@ApiModel(value = "PatientRelative对象", description = "患者家属信息表")
public class PatientRelative implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("患者id")
    private Integer patientId;

    @ApiModelProperty("家属姓名")
    private String name;

    @ApiModelProperty("与患者的关系")
    private String relationship;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("联系地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
