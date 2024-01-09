package cn.edu.just.hostpital.system.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态枚举
 *
 * @author javgo.cn
 * @date 2024/1/8
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResultType implements IErrorCode {

    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未认证（签名错误）"),
    NOT_FOUND(404, "接口不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    PARAMETER_ERROR(1001, "参数错误"),
    BUSINESS_ERROR(1002, "业务错误"),
    NO_PERMISSION(1003, "没有操作权限"),
    NOT_SUPPORTED(1004, "不支持或已经废弃"),
    INVALID_AUTHCODE(1005, "无效的AuthCode"),
    TOO_FREQUENT(1006, "太频繁的调用"),
    UNKNOWN_ERROR(9999, "未知错误");

    private final int code;
    private final String desc;

    @Override
    public long getCode() {
        return 0;
    }

    @Override
    public String getDesc() {
        return null;
    }

    public static ResultType of(int code) {
        for (ResultType resultType : ResultType.values()) {
            if (resultType.getCode() == code) {
                return resultType;
            }
        }
        return null;
    }
}
