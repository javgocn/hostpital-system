package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 财务状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FinancialType {

    WAIT(0, "待审核"),
    PASS(1, "已审核"),
    NOT_PASS(2, "审核不通过"),
    DELETE(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static FinancialType of(Integer code) {
        for (FinancialType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
