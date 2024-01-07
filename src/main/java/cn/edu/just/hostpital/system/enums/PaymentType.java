package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态枚举
 * @author javgo.cn
 * @date 2024/1/7
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PaymentType {

    UNPAID(0, "待支付"),
    PAID(1, "已支付"),
    REFUND(2, "已退款");

    private final Integer code;
    private final String desc;

    public static PaymentType of(Integer code) {
        for (PaymentType value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
