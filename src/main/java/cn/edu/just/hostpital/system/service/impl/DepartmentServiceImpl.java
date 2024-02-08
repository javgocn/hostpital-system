package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.DepartmentDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.DoctorInfoMapper;
import cn.edu.just.hostpital.system.mapper.NurseInfoMapper;
import cn.edu.just.hostpital.system.model.Department;
import cn.edu.just.hostpital.system.mapper.DepartmentMapper;
import cn.edu.just.hostpital.system.model.DoctorInfo;
import cn.edu.just.hostpital.system.model.NurseInfo;
import cn.edu.just.hostpital.system.service.DepartmentService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 科室信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Resource
    private NurseInfoMapper nurseInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, DepartmentDTO departmentDTO) {
        IPage<Department> page = new Page<>(currentPage, size);
        IPage<Department> departmentIPage = null;
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(departmentDTO)) {
            Department department = DataTransferUtil.shallowCopy(departmentDTO, Department.class);
            if (StringUtils.isNotBlank(department.getDepartmentName())) {
                queryWrapper.like("department_name", department.getDepartmentName());
            }
        }
        queryWrapper.ne("status", StatusType.DELETED.getCode());
        departmentIPage = departmentMapper.selectPage(page, queryWrapper);
        return Result.success(departmentIPage);
    }

    @Override
    public Result<?> selectAll() {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", StatusType.ENABLE.getCode());
        return Result.success(departmentMapper.selectList(queryWrapper));
    }

    @Override
    public Result<?> add(DepartmentDTO departmentDTO) {
        departmentDTO.setCreateTime(new Date());
        departmentDTO.setUpdateTime(new Date());
        departmentDTO.setStatus(StatusType.ENABLE.getCode());
        Department department = DataTransferUtil.shallowCopy(departmentDTO, Department.class);
        try {
            departmentMapper.insert(department);
        } catch (Exception e) {
            return Result.fail("科室已存在，请勿重复添加");
        }
        return Result.success("添加成功");
    }

    @Override
    public Result<?> delete(Integer id) {
        QueryWrapper<DoctorInfo> doctorInfoQueryWrapper = new QueryWrapper<>();
        doctorInfoQueryWrapper.eq("department_id", id);
        Long doctorCount = doctorInfoMapper.selectCount(doctorInfoQueryWrapper);
        if (doctorCount > 0) {
            return Result.fail("删除失败，该部门下还存在绑定医生");
        }

        QueryWrapper<NurseInfo> nurseInfoQueryWrapper = new QueryWrapper<>();
        nurseInfoQueryWrapper.eq("department_id", id);
        Long nurseCount = nurseInfoMapper.selectCount(nurseInfoQueryWrapper);
        if (nurseCount > 0) {
            return Result.fail("删除失败，该部门下还存在绑定护士");
        }

        int count = departmentMapper.deleteById(id);
        return count == 1 ? Result.success("删除成功") : Result.fail("删除失败");
    }

    @Override
    public Result<?> update(DepartmentDTO departmentDTO) {
        Department department = DataTransferUtil.shallowCopy(departmentDTO, Department.class);
        int count = departmentMapper.updateById(department);
        return count == 1 ? Result.success(department, "更新成功") : Result.fail("更新失败");
    }
}
