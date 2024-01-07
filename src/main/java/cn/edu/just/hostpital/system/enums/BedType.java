package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 床位状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BedType {

    FREE(0, "空闲"),
    OCCUPY(1, "占用"),
    REPAIR(2, "维修"),
    BOOK(3, "预定"),
    DELETE(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static BedType of(Integer code) {
        for (BedType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
