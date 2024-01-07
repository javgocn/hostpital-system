package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 考勤状态枚举
 *
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AttendanceType {

    NORMAL(0, "正常"),
    LATE(1, "迟到"),
    LEAVE_EARLY(2, "早退"),
    ABSENTEEISM(3, "旷工"),
    LEAVE(4, "请假"),
    OVERTIME(5, "加班"),
    BUSINESS_TRIP(6, "出差"),
    FIELD_WORK(7, "外勤"),
    REST(8, "休息"),
    HOLIDAY(9, "节假日"),
    REST_DAY(10, "调休"),
    OTHER(11, "其他"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String desc;

    public static AttendanceType of(Integer code) {
        for (AttendanceType type : AttendanceType.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
