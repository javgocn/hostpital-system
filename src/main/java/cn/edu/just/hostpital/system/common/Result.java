package cn.edu.just.hostpital.system.common;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String code;

    /**
     * 返回信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL) // 为空时不序列化
    private String msg;

    /**
     * 返回数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 返回数据总数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果，不带数据
     *
     * @return 返回封装了成功状态码和信息的结果对象
     */
    public static <T> Result<T> success() {
        return new Result<>(ResponseStatus.SUCCESS.getResponseCode(), ResponseStatus.SUCCESS.getDescription(), null);
    }

    /**
     * 成功返回结果，带信息
     * @param message 返回的信息
     * @return 返回封装了成功状态码和信息的结果对象
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(ResponseStatus.SUCCESS.getResponseCode(), message, null);
    }

    /**
     * 成功返回结果，带数据
     *
     * @param data 返回的数据
     * @return 返回封装了成功状态码、信息和数据的结果对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseStatus.SUCCESS.getResponseCode(), ResponseStatus.SUCCESS.getDescription(), data);
    }

    /**
     * 成功返回结果，带数据和信息
     *
     * @param data 返回的数据
     * @param message 返回的信息
     * @return 返回封装了成功状态码、信息和数据的结果对象
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(ResponseStatus.SUCCESS.getResponseCode(), message, data);
    }

    /**
     * 失败返回结果，不带数据
     *
     * @return 返回封装了失败状态码和信息的结果对象
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResponseStatus.FAIL.getResponseCode(), ResponseStatus.FAIL.getDescription(), null);
    }

    /**
     * 失败返回结果，带数据
     *
     * @param data 返回的数据
     * @return 返回封装了失败状态码、信息和数据的结果对象
     */
    public static <T> Result<T> fail(T data) {
        return new Result<>(ResponseStatus.FAIL.getResponseCode(), ResponseStatus.FAIL.getDescription(), data);
    }

    /**
     * 失败返回结果，自定义错误信息
     *
     * @param message 自定义的错误信息
     * @return 返回封装了失败状态码和自定义错误信息的结果对象
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(ResponseStatus.FAIL.getResponseCode(), message, null);
    }

    /**
     * 失败返回结果，自定义错误码和消息
     *
     * @param responseCode 自定义的错误码
     * @param message      自定义的错误信息
     * @return 返回封装了自定义错误码和错误信息的结果对象
     */
    public static <T> Result<T> fail(String responseCode, String message) {
        return new Result<>(responseCode, message, null);
    }

    /**
     * 使用枚举返回结果，带数据
     *
     * @param status 指定的响应状态枚举
     * @param data   返回的数据
     * @return 返回封装了指定响应状态码、信息和数据的结果对象
     */
    public static <T> Result<T> of(ResponseStatus status, T data) {
        return new Result<>(status.getResponseCode(), status.getDescription(), data);
    }

    /**
     * 判断操作是否成功
     *
     * @return 如果返回码为 SUCCESS 状态码，则为true
     */
    public boolean isSuccess() {
        return ResponseStatus.SUCCESS.getResponseCode().equals(this.code);
    }

    /**
     * 判断操作是否失败
     *
     * @return 如果返回码不为 SUCCESS 状态码，则为true
     */
    public boolean isFail() {
        return !isSuccess();
    }
}
