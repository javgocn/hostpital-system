package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 *
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SexType {

    WOMAN(0, "女性"),
    MAN(1, "男性"),
    SECRET(2, "保密");

    private final Integer code;
    private final String desc;

    public static SexType of(Integer code) {
        for (SexType sexType : SexType.values()) {
            if (sexType.getCode().equals(code)) {
                return sexType;
            }
        }
        return null;
    }
}
