package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 反馈状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FeedbackType {

    UNREPLIED(0, "待回复"),
    REPLIED(1, "已回复"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static FeedbackType of(Integer code) {
        for (FeedbackType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
