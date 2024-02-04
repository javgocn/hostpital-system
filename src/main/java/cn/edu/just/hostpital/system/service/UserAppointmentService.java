package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserAppointmentDTO;
import cn.edu.just.hostpital.system.model.UserAppointment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户预约表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface UserAppointmentService extends IService<UserAppointment> {

    /**
     * 分页查询
     *
     * @param currentPage        当前页
     * @param size               每页大小
     * @param userAppointmentReq 查询条件
     * @return 用户预约列表（分页）
     */
    Result<?> selectByPage(Integer currentPage, Integer size, UserAppointmentDTO userAppointmentDTO);

    /**
     * 删除多余的预约记录（带就诊）
     *
     * @return 删除结果
     */
    Result<?> delete();

    /**
     * 用户预约
     *
     * @param userAppointmentReq 预约信息
     * @return 预约结果
     */
    Result<?> appointmentWithDoctor(UserAppointmentDTO userAppointmentDTO);

    /**
     * 完成预约
     *
     * @param appointmentId 预约id
     * @return 完成结果
     */
    Result<?> finishAppointment(Integer appointmentId);

    /**
     * 取消预约
     *
     * @param appointmentId 预约id
     * @return 取消结果
     */
    Result<?> cancelAppointment(Integer appointmentId);
}
