package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 处方状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PrescriptionType {

    UNAUDITED(0, "未审核"),
    AUDITED(1, "已审核"),
    NOT_AUDITED(2, "审核不通过"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static PrescriptionType of(Integer code) {
        for (PrescriptionType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
