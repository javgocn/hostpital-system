package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 病例状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MedicalRecordType {

    NOT_TREATED(0, "未就诊"),
    TREATED(1, "已就诊"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static MedicalRecordType of(Integer code) {
        for (MedicalRecordType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
