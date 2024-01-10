package cn.edu.just.hostpital.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户挂号表
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Getter
@Setter
@TableName("user_registration")
@ApiModel(value = "UserRegistration对象", description = "用户挂号表")
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("挂号id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("医生id")
    private Integer doctorId;

    @ApiModelProperty("科室id")
    private Integer deptId;

    @ApiModelProperty("挂号日期")
    private Date registerDate;

    @ApiModelProperty("挂号时间")
    private String registerTime;

    @ApiModelProperty("挂号费用")
    private BigDecimal price;

    @ApiModelProperty("挂号状态：0 待挂号，1 已挂号，2 已取消，3 已叫号，-1 已删除")
    private Integer status;

    @ApiModelProperty("挂号备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
