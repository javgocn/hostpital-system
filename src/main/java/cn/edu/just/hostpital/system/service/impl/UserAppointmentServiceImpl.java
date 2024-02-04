package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserAppointmentDTO;
import cn.edu.just.hostpital.system.enums.AppointmentType;
import cn.edu.just.hostpital.system.mapper.DepartmentMapper;
import cn.edu.just.hostpital.system.mapper.UserInfoMapper;
import cn.edu.just.hostpital.system.model.UserAppointment;
import cn.edu.just.hostpital.system.mapper.UserAppointmentMapper;
import cn.edu.just.hostpital.system.model.UserInfo;
import cn.edu.just.hostpital.system.service.UserAppointmentService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户预约表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class UserAppointmentServiceImpl extends ServiceImpl<UserAppointmentMapper, UserAppointment> implements UserAppointmentService {

    @Resource
    private UserAppointmentMapper userAppointmentMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, UserAppointmentDTO userAppointmentDTO) {
        IPage<UserAppointment> page = new Page<>(currentPage, size);
        IPage<UserAppointment> appointmentIPage = null;
        UserAppointment userAppointment = DataTransferUtil.shallowCopy(userAppointmentDTO, UserAppointment.class);
        QueryWrapper<UserAppointment> appointmentQueryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(userAppointment.getCreateTime())) {
            appointmentQueryWrapper.eq("create_time", userAppointment.getCreateTime());
        }
        if (Objects.nonNull(userAppointment.getConsultTime())) {
            appointmentQueryWrapper.eq("consult_time", userAppointment.getConsultTime());
        }
        if (Objects.nonNull(userAppointmentDTO.getUserName())) {
            String userName = userAppointmentDTO.getUserName();
            QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
            userInfoQueryWrapper.eq("name", userName);
            UserInfo userInfo = userInfoMapper.selectOne(userInfoQueryWrapper);
            if (Objects.isNull(userInfo)) {
                return Result.fail("用户不存在");
            }
            appointmentQueryWrapper.eq("user_id", userInfo.getId());
        }
        if (Objects.nonNull(userAppointment.getDeptId())) {
            appointmentQueryWrapper.eq("dept_id", userAppointment.getDeptId());
        }
        if (Objects.nonNull(userAppointment.getStatus())) {
            appointmentQueryWrapper.eq("status", userAppointment.getStatus());
        }
        appointmentIPage = userAppointmentMapper.selectPage(page, appointmentQueryWrapper);
        return Result.success(appointmentIPage);
    }

    @Override
    public Result<?> delete() {
        QueryWrapper<UserAppointment> queryWrapper = new QueryWrapper<>();
        // 状态 = 待就诊
        queryWrapper.eq("status", AppointmentType.WAIT.getCode());
        // 删除前一天的预约
        LocalDate currentDate = LocalDate.now();
        LocalDate previousDate = currentDate.minusDays(1);
        Date date = Date.from(previousDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        queryWrapper.le("create_time", date);
        userAppointmentMapper.delete(queryWrapper);
        return Result.success();
    }

    @Override
    public Result<?> appointmentWithDoctor(UserAppointmentDTO userAppointmentDTO) {
        UserAppointment userAppointment = DataTransferUtil.shallowCopy(userAppointmentDTO, UserAppointment.class);
        if (Objects.isNull(userAppointment.getUserId())) {
            return Result.fail("用户id不能为空");
        }
        if (Objects.isNull(userAppointment.getDoctorId())) {
            return Result.fail("医生id不能为空");
        }
        if (Objects.isNull(userAppointment.getDeptId())) {
            return Result.fail("科室id不能为空");
        }
        if (Objects.isNull(userAppointment.getCreateTime())) {
            return Result.fail("预约时间不能为空");
        }

        userAppointment.setStatus(AppointmentType.WAIT.getCode());
        return userAppointmentMapper.insert(userAppointment) > 0 ? Result.success("预约成功") : Result.fail("预约失败");
    }

    @Override
    public Result<?> finishAppointment(Integer appointmentId) {
        if (Objects.isNull(appointmentId)) {
            return Result.fail("预约id不能为空");
        }
        UserAppointment userAppointment = userAppointmentMapper.selectById(appointmentId);
        if (Objects.isNull(userAppointment)) {
            return Result.fail("预约不存在");
        }
        userAppointment.setConsultTime(new Date());
        userAppointment.setStatus(AppointmentType.FINISH.getCode());
        return userAppointmentMapper.updateById(userAppointment) > 0 ? Result.success("就诊成功") : Result.fail("就诊失败");
    }

    @Override
    public Result<?> cancelAppointment(Integer appointmentId) {
        if (Objects.isNull(appointmentId)) {
            return Result.fail("预约id不能为空");
        }
        UserAppointment userAppointment = userAppointmentMapper.selectById(appointmentId);
        if (Objects.isNull(userAppointment)) {
            return Result.fail("预约不存在");
        }
        userAppointment.setStatus(AppointmentType.CANCEL.getCode());
        return userAppointmentMapper.updateById(userAppointment) > 0 ? Result.success("取消成功") : Result.fail("取消失败");
    }
}
