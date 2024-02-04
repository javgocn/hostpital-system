package cn.edu.just.hostpital.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页数据 VO
 *
 * @author javgo.cn
 * @date 2024/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumVO {

    /**
     * 用户数量
     */
    private Long userNum;

    /**
     * 医生数量
     */
    private Long doctorNum;

    /**
     * 反馈数量
     */
    private Long feedBackNum;

    /**
     * 预约数量
     */
    private Long appointNum;
}
