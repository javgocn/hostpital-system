package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.constans.UserConstants;
import cn.edu.just.hostpital.system.dto.DoctorInfoDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.DepartmentMapper;
import cn.edu.just.hostpital.system.mapper.PositionInfoMapper;
import cn.edu.just.hostpital.system.model.Department;
import cn.edu.just.hostpital.system.model.DoctorInfo;
import cn.edu.just.hostpital.system.mapper.DoctorInfoMapper;
import cn.edu.just.hostpital.system.model.PositionInfo;
import cn.edu.just.hostpital.system.dto.DoctorInfoDTO;
import cn.edu.just.hostpital.system.service.DoctorInfoService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import cn.edu.just.hostpital.system.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 医生信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Slf4j
@Service
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorInfoService {

    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PositionInfoMapper positionInfoMapper;

    @Override
    public Result<?> login(DoctorInfoDTO DoctorInfoDTO, HttpServletRequest request) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(DoctorInfoDTO, DoctorInfo.class);
        if (Objects.isNull(doctorInfo.getUsername())) {
            return Result.fail("用户名不能为空");
        }
        if (Objects.isNull(doctorInfo.getPassword())) {
            return Result.fail("密码不能为空");
        }
        String pwdMD5 = MD5Util.md5(doctorInfo.getPassword(), UserConstants.USER_SLAT);
        QueryWrapper<DoctorInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", doctorInfo.getUsername());
        DoctorInfo existDoctorInfo = doctorInfoMapper.selectOne(queryWrapper);
        if (Objects.isNull(existDoctorInfo)) {
            return Result.fail("用户名不存在");
        }
        if (!pwdMD5.equals(existDoctorInfo.getPassword())) {
            return Result.fail("密码错误");
        }
        if (Objects.equals(existDoctorInfo.getStatus(), StatusType.DISABLE.getCode())) {
            return Result.fail("该用户已被锁定");
        }
        if (Objects.equals(existDoctorInfo.getStatus(), StatusType.DELETED.getCode())) {
            return Result.fail("该用户已被删除");
        }
        DoctorInfoDTO doctorInfoDTO = DataTransferUtil.shallowCopy(existDoctorInfo, DoctorInfoDTO.class);
        request.getSession().setAttribute("doctorInfo", doctorInfoDTO);
        return Result.success(doctorInfoDTO, "登录成功");
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        log.info("用户 {} 退出登录", req.getSession().getAttribute("doctorInfo"));
        req.getSession().removeAttribute("doctorInfo");
        return Result.success("退出登录成功");
    }

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, DoctorInfoDTO DoctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(DoctorInfoDTO, DoctorInfo.class);
        IPage<DoctorInfo> page = new Page<>(currentPage, size);
        IPage<DoctorInfo> doctorInfoIPage = null;
        QueryWrapper<DoctorInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(doctorInfo.getUsername()) && StringUtils.isBlank(doctorInfo.getName())) {
            queryWrapper.eq("username", doctorInfo.getUsername());
        }
        if (StringUtils.isBlank(doctorInfo.getUsername()) && StringUtils.isNotBlank(doctorInfo.getName())) {
            queryWrapper.eq("name", doctorInfo.getName());
        }
        if (StringUtils.isNotBlank(doctorInfo.getUsername()) && StringUtils.isNotBlank(doctorInfo.getName())) {
            queryWrapper.eq("name", doctorInfo.getName());
            queryWrapper.eq("username", doctorInfo.getUsername());
        }
        if (StringUtils.isNotBlank(doctorInfo.getJobNumber())) {
            queryWrapper.eq("job_number", doctorInfo.getJobNumber());
        }
        if (Objects.nonNull(doctorInfo.getDepartmentId())) {
            queryWrapper.eq("department_id", doctorInfo.getDepartmentId());
        }
        if (Objects.nonNull(doctorInfo.getPositionId())) {
            queryWrapper.eq("position_id", doctorInfo.getPositionId());
        }
        doctorInfoIPage = doctorInfoMapper.selectPage(page, queryWrapper);
        return Result.success(doctorInfoIPage, "查询成功");
    }

    @Override
    public Result<?> findById(Integer id) {
        if (Objects.isNull(id)) {
            return Result.fail("id不能为空");
        }
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(id);
        if (Objects.isNull(doctorInfo)) {
            return Result.fail("该医生不存在");
        }
        DoctorInfoDTO doctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        return Result.success(doctorInfoDTO, "查询成功");
    }

    @Override
    public Result<?> add(DoctorInfoDTO DoctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(DoctorInfoDTO, DoctorInfo.class);
        if (StringUtils.isBlank(doctorInfo.getUsername())) {
            return Result.fail("用户名不能为空");
        }
        if (StringUtils.isBlank(doctorInfo.getPassword())) {
            return Result.fail("密码不能为空");
        }
        if (StringUtils.isBlank(doctorInfo.getName())) {
            return Result.fail("姓名不能为空");
        }
        if (Objects.isNull(doctorInfo.getDepartmentId())) {
            return Result.fail("部门不能为空");
        }
        if (Objects.isNull(doctorInfo.getPositionId())) {
            return Result.fail("职位不能为空");
        }
        if (StringUtils.isBlank(doctorInfo.getTelephone())) {
            return Result.fail("电话不能为空");
        }
        if (StringUtils.isBlank(doctorInfo.getEmail())) {
            return Result.fail("邮箱不能为空");
        }
        if (Objects.isNull(doctorInfo.getSex())) {
            return Result.fail("性别不能为空");
        }
        if (Objects.isNull(doctorInfo.getBirthday())) {
            return Result.fail("生日不能为空");
        }
        if (StringUtils.isBlank(doctorInfo.getAddress())) {
            return Result.fail("住址不能为空");
        }
        doctorInfo.setPassword(MD5Util.md5(doctorInfo.getPassword(), UserConstants.USER_SLAT));
        doctorInfo.setStatus(StatusType.ENABLE.getCode());
        doctorInfo.setJobNumber(generateJobNumber());
        doctorInfo.setCreateTime(new Date());
        doctorInfo.setUpdateTime(new Date());
        int insert = doctorInfoMapper.insert(doctorInfo);
        DoctorInfoDTO doctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        Department department = departmentMapper.selectById(doctorInfo.getDepartmentId());
        doctorInfoDTO.setDepartmentName(Optional.ofNullable(department).map(Department::getName).orElse(""));
        PositionInfo positionInfo = positionInfoMapper.selectById(doctorInfo.getPositionId());
        doctorInfoDTO.setPositionName(Optional.ofNullable(positionInfo).map(PositionInfo::getPositionName).orElse(""));
        return insert > 0 ? Result.success(doctorInfoDTO, "添加成功") : Result.fail("添加失败");
    }

    private synchronized String generateJobNumber() {
        int maxJobNumber = doctorInfoMapper.getMaxJobNumber();
        maxJobNumber++;
        return String.format("%05d", maxJobNumber);
    }

    @Override
    public Result<?> lock(DoctorInfoDTO DoctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(DoctorInfoDTO, DoctorInfo.class);
        if (Objects.isNull(doctorInfo.getId())) {
            return Result.fail("id不能为空");
        }
        if (Objects.isNull(doctorInfo.getStatus())) {
            return Result.fail("状态不能为空");
        }
        if (doctorInfo.getStatus().equals(StatusType.ENABLE.getCode())) {
            doctorInfoMapper.updateById(doctorInfo);
            return Result.success("解锁成功");
        }
        if (doctorInfo.getStatus().equals(StatusType.DISABLE.getCode())) {
            doctorInfoMapper.updateById(doctorInfo);
            return Result.success("锁定成功");
        }
        return Result.fail("状态错误");
    }

    @Override
    public Result<?> delete(Integer id) {
        if (Objects.isNull(id)) {
            return Result.fail("id不能为空");
        }
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(id);
        if (Objects.isNull(doctorInfo)) {
            return Result.fail("该医生不存在");
        }
        doctorInfo.setStatus(StatusType.DELETED.getCode());
        doctorInfoMapper.updateById(doctorInfo);
        return Result.success("删除成功");
    }

    @Override
    public Result<?> update(DoctorInfoDTO DoctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(DoctorInfoDTO, DoctorInfo.class);
        doctorInfo.setUpdateTime(new Date());
        int update = doctorInfoMapper.updateById(doctorInfo);
        DoctorInfoDTO doctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        Department department = departmentMapper.selectById(doctorInfo.getDepartmentId());
        doctorInfoDTO.setDepartmentName(Optional.ofNullable(department).map(Department::getName).orElse(""));
        PositionInfo positionInfo = positionInfoMapper.selectById(doctorInfo.getPositionId());
        doctorInfoDTO.setPositionName(Optional.ofNullable(positionInfo).map(PositionInfo::getPositionName).orElse(""));
        return update > 0 ? Result.success(doctorInfoDTO, "更新成功") : Result.fail("更新失败");
    }

    @Override
    public Result<?> resetPassword(String pwd, String newPwd, Integer id) {
        if (Objects.isNull(id)) {
            return Result.fail("id不能为空");
        }
        if (StringUtils.isBlank(pwd)) {
            return Result.fail("旧密码不能为空");
        }
        if (StringUtils.isBlank(newPwd)) {
            return Result.fail("新密码不能为空");
        }
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(id);
        if (Objects.isNull(doctorInfo)) {
            return Result.fail("该医生不存在");
        }
        String pwdMD5 = MD5Util.md5(pwd, UserConstants.USER_SLAT);
        if (!pwdMD5.equals(doctorInfo.getPassword())) {
            return Result.fail("旧密码错误");
        }
        doctorInfo.setPassword(MD5Util.md5(newPwd, UserConstants.USER_SLAT));
        doctorInfoMapper.updateById(doctorInfo);
        return Result.success("重置密码成功");
    }
}
