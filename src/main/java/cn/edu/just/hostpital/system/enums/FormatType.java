package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常见参数返回格式枚举
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FormatType {

    JSON("JSON"),
    XML("XML");

    private final String format;

    public static FormatType of(String format) {
        for (FormatType formatType : FormatType.values()) {
            if (formatType.getFormat().equals(format)) {
                return formatType;
            }
        }
        return null;
    }
}
