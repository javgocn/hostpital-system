package cn.edu.just.hostpital.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 通用返回结果
 *
 * @author javgo.cn
 * @date 2024/1/8
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private long code;

    /**
     * 返回信息
     */
    private String desc;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getDesc(), data);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ResultType.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(IErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getDesc(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> Result<T> failed(IErrorCode errorCode, String message) {
        return new Result<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<>(ResultType.FAIL.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed(ResultType.FAIL);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Result<T> validateFailed() {
        return failed(ResultType.PARAMETER_ERROR);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> parameterError(String message) {
        return new Result<>(ResultType.PARAMETER_ERROR.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     * @param data 返回数据
     */
    public static <T> Result<T> unauthorized(T data) {
        return new Result<>(ResultType.UNAUTHORIZED.getCode(), ResultType.UNAUTHORIZED.getDesc(), data);
    }

    /**
     * 未授权返回结果
     * @param data 返回数据
     */
    public static <T> Result<T> noPermission(T data) {
        return new Result<>(ResultType.NO_PERMISSION.getCode(), ResultType.NO_PERMISSION.getDesc(), data);
    }

    /**
     * 是否成功
     * @return true/false
     */
    public boolean isSuccess() {
        return this.code == ResultType.SUCCESS.getCode();
    }
}
