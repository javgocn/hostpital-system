package cn.edu.just.hostpital.system.exception;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.common.ResponseStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author javgo.cn
 * @date 2024/2/4
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 处理方法参数无效的情况
     *
     * @param exception 方法参数无效异常
     * @return 返回封装了参数校验失败状态码和信息的结果对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder errorMessageBuilder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errorMessageBuilder.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(";");
        });
        String errorMessage = errorMessageBuilder.toString().trim();
        if (errorMessage.endsWith(";")) {
            errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
        }
        return Result.fail(ResponseStatus.PARAMETER_VALIDATION_FAILED.getResponseCode(), errorMessage);
    }

    /**
     * 处理约束违反异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        exception.getConstraintViolations().forEach(constraintViolation -> errorMessageBuilder.append(constraintViolation.getMessage()).append(";"));
        String errorMessage = errorMessageBuilder.toString().trim();
        if (errorMessage.endsWith(";")) {
            errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
        }
        return Result.fail(ResponseStatus.PARAMETER_VALIDATION_FAILED.getResponseCode(), errorMessage);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> handleException(Exception exception) {
        return Result.fail(ResponseStatus.BUSINESS_EXCEPTION.getResponseCode(), exception.getMessage());
    }
}
