package cn.edu.just.hostpital.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页统计图 VO
 *
 * @author javgo.cn
 * @date 2024/1/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionVO {

    private Integer[] option;
    private Integer[] option2;
}
