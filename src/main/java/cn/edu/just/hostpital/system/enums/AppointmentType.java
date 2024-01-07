package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 就诊状态枚举
 *
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AppointmentType {

    WAIT(0, "待就诊"),
    FINISH(1, "已就诊"),
    CANCEL(2, "已取消"),
    DELETE(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static AppointmentType of(Integer code) {
        for (AppointmentType type : AppointmentType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
