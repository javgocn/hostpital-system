package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 挂号状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RegistrationType {

    WAIT(0, "待挂号"),
    REGISTERED(1, "已挂号"),
    CANCEL(2, "已取消"),
    CALLED(3, "已叫号"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static RegistrationType of(Integer code) {
        for (RegistrationType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
