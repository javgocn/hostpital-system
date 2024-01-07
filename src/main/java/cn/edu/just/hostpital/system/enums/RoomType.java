package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 病房状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoomType {

    FREE(0, "空闲"),
    AVAILABLE(1, "可用"),
    REPAIR(2, "维修"),
    FULL(3, "已满"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static RoomType of(Integer code) {
        for (RoomType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
