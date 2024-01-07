package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 入库状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StockType {

    UNAUDITED(0, "未审核"),
    AUDITED(1, "已审核"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static StockType of(Integer code) {
        for (StockType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
