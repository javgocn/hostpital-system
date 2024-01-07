package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 采购状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PurchaseType {

    NOT_IN(0, "未入库"),
    IN(1, "已入库"),
    DELETE(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static PurchaseType of(Integer code) {
        for (PurchaseType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
