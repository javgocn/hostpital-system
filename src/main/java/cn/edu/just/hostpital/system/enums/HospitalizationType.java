package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 住院状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HospitalizationType {

    HOSPITALIZATION(0, "住院中"),
    DISCHARGE(1, "已出院"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static HospitalizationType of(Integer code) {
        for (HospitalizationType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
