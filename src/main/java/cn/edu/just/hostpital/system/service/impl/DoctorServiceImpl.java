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
import cn.edu.just.hostpital.system.service.DoctorService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import cn.edu.just.hostpital.system.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
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
public class DoctorServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorService {

    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PositionInfoMapper positionInfoMapper;

    @Override
    public Result<?> login(@Validated DoctorInfoDTO doctorInfoDTO, HttpServletRequest request) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(doctorInfoDTO, DoctorInfo.class);
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
        DoctorInfoDTO result = DataTransferUtil.shallowCopy(existDoctorInfo, DoctorInfoDTO.class);
        request.getSession().setAttribute("doctorInfo", doctorInfoDTO);
        return Result.success(result, "登录成功");
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        log.info("用户 {} 退出登录", req.getSession().getAttribute("doctorInfo"));
        req.getSession().removeAttribute("doctorInfo");
        return Result.success("退出登录成功");
    }

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, DoctorInfoDTO doctorInfoDTO) {
        IPage<DoctorInfo> page = new Page<>(currentPage, size);
        IPage<DoctorInfo> doctorInfoIPage = null;
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(doctorInfoDTO, DoctorInfo.class);
        QueryWrapper<DoctorInfo> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(doctorInfo)) {
            if (StringUtils.isNotBlank(doctorInfo.getUsername())) {
                queryWrapper.like("username", doctorInfo.getUsername());
            }
            if (StringUtils.isNotBlank(doctorInfo.getName())) {
                queryWrapper.like("name", doctorInfo.getName());
            }
            if (StringUtils.isNotBlank(doctorInfo.getJobNumber())) {
                queryWrapper.like("job_number", doctorInfo.getJobNumber());
            }
            if (Objects.nonNull(doctorInfo.getDepartmentId())) {
                queryWrapper.eq("department_id", doctorInfo.getDepartmentId());
            }
            if (Objects.nonNull(doctorInfo.getPositionId())) {
                queryWrapper.eq("position_id", doctorInfo.getPositionId());
            }
        }
        queryWrapper.ne("status", StatusType.DELETED.getCode());
        doctorInfoIPage = doctorInfoMapper.selectPage(page, queryWrapper);
        List<DoctorInfo> records = doctorInfoIPage.getRecords();
        List<DoctorInfoDTO> resultRecords = Lists.newArrayList();
        records.forEach(record -> {
            DoctorInfoDTO result = DataTransferUtil.shallowCopy(record, DoctorInfoDTO.class);
            Department department = departmentMapper.selectById(record.getDepartmentId());
            result.setDepartmentName(Optional.ofNullable(department).map(Department::getDepartmentName).orElse(""));
            PositionInfo positionInfo = positionInfoMapper.selectById(record.getPositionId());
            result.setPositionName(Optional.ofNullable(positionInfo).map(PositionInfo::getPositionName).orElse(""));
            result.setPassword(null);
            resultRecords.add(result);
        });

        IPage<DoctorInfoDTO> doctorInfoIPageResult = new Page<>();
        doctorInfoIPageResult.setRecords(resultRecords);

        return Result.success(doctorInfoIPageResult, "查询成功");
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
        if (Objects.equals(doctorInfo.getStatus(), StatusType.DELETED.getCode())) {
            return Result.fail("该医生已被删除");
        }
        DoctorInfoDTO doctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        return Result.success(doctorInfoDTO, "查询成功");
    }

    @Override
    public Result<?> add(DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(doctorInfoDTO, DoctorInfo.class);
        doctorInfo.setPassword(MD5Util.md5(doctorInfo.getPassword(), UserConstants.USER_SLAT));
        doctorInfo.setStatus(StatusType.ENABLE.getCode());
        doctorInfo.setJobNumber(generateJobNumber());
        doctorInfo.setCreateTime(new Date());
        doctorInfo.setUpdateTime(new Date());
        int insert = doctorInfoMapper.insert(doctorInfo);
        DoctorInfoDTO resultDoctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        Department department = departmentMapper.selectById(doctorInfo.getDepartmentId());
        resultDoctorInfoDTO.setDepartmentName(Optional.ofNullable(department).map(Department::getDepartmentName).orElse(""));
        PositionInfo positionInfo = positionInfoMapper.selectById(doctorInfo.getPositionId());
        resultDoctorInfoDTO.setPositionName(Optional.ofNullable(positionInfo).map(PositionInfo::getPositionName).orElse(""));
        return insert > 0 ? Result.success(resultDoctorInfoDTO, "添加成功") : Result.fail("添加失败");
    }

    private synchronized String generateJobNumber() {
        int maxJobNumber = doctorInfoMapper.getMaxJobNumber();
        maxJobNumber++;
        return String.format("%05d", maxJobNumber);
    }

    @Override
    public Result<?> lock(DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(doctorInfoDTO, DoctorInfo.class);
        if (Objects.isNull(doctorInfo.getId())) {
            return Result.fail("id不能为空");
        }
        if (Objects.isNull(doctorInfo.getStatus())) {
            return Result.fail("状态不能为空");
        }
        if (doctorInfo.getStatus().equals(StatusType.ENABLE.getCode())) {
            doctorInfo.setStatus(StatusType.DISABLE.getCode());
            doctorInfoMapper.updateById(doctorInfo);
            return Result.success("锁定成功");
        }
        if (doctorInfo.getStatus().equals(StatusType.DISABLE.getCode())) {
            doctorInfo.setStatus(StatusType.ENABLE.getCode());
            doctorInfoMapper.updateById(doctorInfo);
            return Result.success("解锁成功");
        }
        return Result.fail("状态错误");
    }

    @Override
    public Result<?> delete(Integer id) {
        DoctorInfo doctorInfo = doctorInfoMapper.selectById(id);
        if (Objects.isNull(doctorInfo)) {
            return Result.fail("该医生不存在");
        }
        doctorInfo.setStatus(StatusType.DELETED.getCode());
        doctorInfoMapper.updateById(doctorInfo);
        return Result.success("删除成功");
    }

    @Override
    public Result<?> update(DoctorInfoDTO doctorInfoDTO) {
        DoctorInfo doctorInfo = DataTransferUtil.shallowCopy(doctorInfoDTO, DoctorInfo.class);
        doctorInfo.setUpdateTime(new Date());
        int update = doctorInfoMapper.updateById(doctorInfo);
        DoctorInfoDTO resultDoctorInfoDTO = DataTransferUtil.shallowCopy(doctorInfo, DoctorInfoDTO.class);
        Department department = departmentMapper.selectById(doctorInfo.getDepartmentId());
        resultDoctorInfoDTO.setDepartmentName(Optional.ofNullable(department).map(Department::getDepartmentName).orElse(""));
        PositionInfo positionInfo = positionInfoMapper.selectById(doctorInfo.getPositionId());
        resultDoctorInfoDTO.setPositionName(Optional.ofNullable(positionInfo).map(PositionInfo::getPositionName).orElse(""));
        return update > 0 ? Result.success(resultDoctorInfoDTO, "更新成功") : Result.fail("更新失败");
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
