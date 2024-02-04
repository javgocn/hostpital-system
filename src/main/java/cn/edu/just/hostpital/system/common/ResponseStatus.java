package cn.edu.just.hostpital.system.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 响应状态枚举
 *
 * @author javgo.cn
 * @date 2024/1/8
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseStatus {

    SUCCESS("200", "success"),
    SERVER_EXCEPTION("500", "failed"),
    BUSINESS_EXCEPTION("400", "business exception"),
    PARAMETER_VALIDATION_FAILED("401", "validate fail"),

    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");

    private final String responseCode;
    private final String description;

    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(Arrays.asList(
            HTTP_STATUS_200,
            HTTP_STATUS_400,
            HTTP_STATUS_401,
            HTTP_STATUS_403,
            HTTP_STATUS_500
    ));

    public static ResponseStatus of(String responseCode) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            if (responseStatus.responseCode.equals(responseCode)) {
                return responseStatus;
            }
        }
        return null;
    }

}
