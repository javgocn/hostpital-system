package cn.edu.just.hostpital.system.common;

/**
 * API 返回码与返回信息接口
 *
 * @author javgo.cn
 * @date 2024/1/8
 */
public interface IErrorCode {

    long getCode();

    String getDesc();
}
