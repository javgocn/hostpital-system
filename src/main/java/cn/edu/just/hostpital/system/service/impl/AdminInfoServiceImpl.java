package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.constans.UserConstants;
import cn.edu.just.hostpital.system.dto.AdminInfoDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.*;
import cn.edu.just.hostpital.system.model.*;
import cn.edu.just.hostpital.system.service.AdminInfoService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import cn.edu.just.hostpital.system.utils.DateUtil;
import cn.edu.just.hostpital.system.utils.MD5Util;
import cn.edu.just.hostpital.system.vo.NumVO;
import cn.edu.just.hostpital.system.vo.OptionVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Slf4j
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Resource
    private UserAppointmentMapper userAppointmentMapper;

    @Resource
    private UserFeedbackMapper userFeedbackMapper;


    @Override
    public Result<?> login(@Validated AdminInfoDTO adminInfoDTO, HttpServletRequest request) {
        AdminInfo adminInfo = DataTransferUtil.shallowCopy(adminInfoDTO, AdminInfo.class);
        String md5Pwd = MD5Util.md5(adminInfo.getPassword(), UserConstants.USER_SLAT);

        QueryWrapper<AdminInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", adminInfo.getUsername());
        queryWrapper.eq("password", md5Pwd);
        adminInfo = adminInfoMapper.selectOne(queryWrapper);

        if (Objects.isNull(adminInfo)) {
            return Result.fail("登录失败，用户名或密码错误");
        }
        if (Objects.equals(adminInfo.getStatus(), StatusType.DISABLE.getCode())) {
            return Result.fail("登录失败，管理员已被锁定");
        }
        if (Objects.equals(adminInfo.getStatus(), StatusType.DELETED.getCode())) {
            return Result.fail("登录失败，管理员已被删除");
        }

        request.getSession().setAttribute("admin", adminInfo.getName());
        return Result.success(DataTransferUtil.shallowCopy(adminInfo, AdminInfoDTO.class));
    }

    @Override
    public Result<?> add(AdminInfoDTO adminInfoDTO) {
        if (StringUtils.isBlank(adminInfoDTO.getUsername())) {
            return Result.fail("用户名不能为空");
        }
        if (StringUtils.isBlank(adminInfoDTO.getPassword())) {
            return Result.fail("密码不能为空");
        }
        if (StringUtils.isBlank(adminInfoDTO.getName())) {
            return Result.fail("姓名不能为空");
        }
        if (StringUtils.isBlank(adminInfoDTO.getEmail())) {
            return Result.fail("邮箱不能为空");
        }
        if (StringUtils.isBlank(adminInfoDTO.getTel())) {
            return Result.fail("手机号不能为空");
        }

        AdminInfo adminInfo = DataTransferUtil.shallowCopy(adminInfoDTO, AdminInfo.class);
        String md5Pwd = MD5Util.md5(adminInfo.getPassword(), UserConstants.USER_SLAT);
        adminInfo.setPassword(md5Pwd);
        adminInfo.setStatus(StatusType.ENABLE.getCode());
        adminInfoMapper.insert(adminInfo);
        return Result.success("添加成功");
    }

    @Override
    public Result<?> resetPwd(String pwd, String newPwd, Integer id) {
        if (Objects.isNull(id)) {
            return Result.fail("管理员id不能为空");
        }
        if (StringUtils.isBlank(pwd)) {
            return Result.fail("原密码不能为空");
        }
        if (StringUtils.isBlank(newPwd)) {
            return Result.fail("新密码不能为空");
        }

        String pwdMD5 = MD5Util.md5(pwd, UserConstants.USER_SLAT);
        String newPwdMD5 = MD5Util.md5(newPwd, UserConstants.USER_SLAT);

        QueryWrapper<AdminInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        AdminInfo adminInfo = adminInfoMapper.selectOne(queryWrapper);

        if (Objects.equals(adminInfo.getPassword(), pwdMD5)) {
            adminInfo.setPassword(newPwdMD5);
            adminInfoMapper.updateById(adminInfo);
            return Result.success("密码修改成功");
        }
        return Result.fail("原密码错误");
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        log.info("管理员 {} 退出登录", req.getSession().getAttribute("admin"));
        req.getSession().removeAttribute("admin");
        return Result.success("退出成功");
    }

    @Override
    public Result<?> getNum() {
        NumVO numVO = new NumVO();

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long userNum = userInfoMapper.selectCount(userInfoQueryWrapper);
        numVO.setUsernum(userNum);

        QueryWrapper<DoctorInfo> doctorInfoQueryWrapper = new QueryWrapper<>();
        doctorInfoQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long doctorNum = doctorInfoMapper.selectCount(doctorInfoQueryWrapper);
        numVO.setDoctornum(doctorNum);

        QueryWrapper<UserFeedback> userFeedbackQueryWrapper = new QueryWrapper<>();
        userFeedbackQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long feedbackNum = userFeedbackMapper.selectCount(userFeedbackQueryWrapper);
        numVO.setMessagenum(feedbackNum);

        QueryWrapper<UserAppointment> userAppointmentQueryWrapper = new QueryWrapper<>();
        userAppointmentQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long appointmentNum = userAppointmentMapper.selectCount(userAppointmentQueryWrapper);
        numVO.setAppointnum(appointmentNum);

        return Result.success(numVO);
    }

    @Override
    public Result<?> getPhoto() {
        String year = String.valueOf(DateUtil.getYear());
        OptionVO optionVO = new OptionVO();

        Integer[] option1 = {
                userInfoMapper.selectByCreateTime(year, "01"),
                userInfoMapper.selectByCreateTime(year, "02"),
                userInfoMapper.selectByCreateTime(year, "03"),
                userInfoMapper.selectByCreateTime(year, "04"),
                userInfoMapper.selectByCreateTime(year, "05"),
                userInfoMapper.selectByCreateTime(year, "06"),
                userInfoMapper.selectByCreateTime(year, "07"),
                userInfoMapper.selectByCreateTime(year, "08"),
                userInfoMapper.selectByCreateTime(year, "09"),
                userInfoMapper.selectByCreateTime(year, "10"),
                userInfoMapper.selectByCreateTime(year, "11"),
                userInfoMapper.selectByCreateTime(year, "12")
        };

        Integer[] option2 = {
                userAppointmentMapper.selectByCreateTime(year, "01"),
                userAppointmentMapper.selectByCreateTime(year, "02"),
                userAppointmentMapper.selectByCreateTime(year, "03"),
                userAppointmentMapper.selectByCreateTime(year, "04"),
                userAppointmentMapper.selectByCreateTime(year, "05"),
                userAppointmentMapper.selectByCreateTime(year, "06"),
                userAppointmentMapper.selectByCreateTime(year, "07"),
                userAppointmentMapper.selectByCreateTime(year, "08"),
                userAppointmentMapper.selectByCreateTime(year, "09"),
                userAppointmentMapper.selectByCreateTime(year, "10"),
                userAppointmentMapper.selectByCreateTime(year, "11"),
                userAppointmentMapper.selectByCreateTime(year, "12")
        };

        optionVO.setOption(option1);
        optionVO.setOption2(option2);
        return Result.success(optionVO);
    }
}
