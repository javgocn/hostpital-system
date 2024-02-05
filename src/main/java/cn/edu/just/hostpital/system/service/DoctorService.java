package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.DoctorInfo;
import cn.edu.just.hostpital.system.dto.DoctorInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 医生信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface DoctorService extends IService<DoctorInfo> {

    /**
     * 医生登录
     *
     * @param DoctorInfoDTO 医生信息请求类
     * @param request       请求
     * @return Result
     */
    Result<?> login(DoctorInfoDTO DoctorInfoDTO, HttpServletRequest request);

    /**
     * 医生登出
     *
     * @param req 请求
     * @param res 响应
     * @return Result
     */
    Result<?> logout(HttpServletRequest req, HttpServletResponse res);

    /**
     * 分页查找医生信息
     *
     * @param currentPage   当前页
     * @param size          每页大小
     * @param DoctorInfoDTO 医生信息请求类
     * @return Result
     */
    Result<?> selectByPage(Integer currentPage, Integer size, DoctorInfoDTO DoctorInfoDTO);

    /**
     * 根据id查找医生信息
     *
     * @param id 医生信息id
     * @return Result
     */
    Result<?> findById(Integer id);

    /**
     * 添加医生信息
     *
     * @param DoctorInfoDTO 医生信息请求类
     * @return Result
     */
    Result<?> add(DoctorInfoDTO doctorInfoDTO);

    /**
     * 锁定 / 解锁 医生信息
     *
     * @param DoctorInfoDTO 医生信息请求类
     * @return Result
     */
    Result<?> lock(DoctorInfoDTO doctorInfoDTO);

    /**
     * 删除医生信息
     *
     * @param id 医生信息id
     * @return Result
     */
    Result<?> delete(Integer id);

    /**
     * 更新医生信息
     *
     * @param DoctorInfoDTO 医生信息请求类
     * @return Result
     */
    Result<?> update(DoctorInfoDTO doctorInfoDTO);

    /**
     * 重置密码
     *
     * @param pwd    旧密码
     * @param newPwd 新密码
     * @param id     医生信息id
     * @return Result
     */
    Result<?> resetPassword(String pwd, String newPwd, Integer id);
}
