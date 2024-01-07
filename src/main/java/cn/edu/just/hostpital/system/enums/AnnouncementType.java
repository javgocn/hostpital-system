package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公告类型枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AnnouncementType {

    UNPUBLISHED(0, "未发布"),
    PUBLISHED(1, "已发布"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static AnnouncementType of(Integer code) {
        for (AnnouncementType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
