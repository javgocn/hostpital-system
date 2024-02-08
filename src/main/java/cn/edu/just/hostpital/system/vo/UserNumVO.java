package cn.edu.just.hostpital.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户菜单 VO
 *
 * @author javgo.cn
 * @date 2024/1/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNumVO {

    /**
     * 部门数量
     */
    private Long deptNum;

    /**
     * 医生数量
     */
    private Long doctorNum;

    /**
     * 病床数量
     */
    private Long bedNum;

    /**
     * 通知数量
     */
    private Long noticeNum;
}
