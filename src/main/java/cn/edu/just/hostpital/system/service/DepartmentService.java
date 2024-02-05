package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.DepartmentDTO;
import cn.edu.just.hostpital.system.model.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 科室信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 分页查询科室信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @param departmentDTO 科室信息 DTO
     * @return 科室列表
     */
    Result<?> selectByPage(Integer currentPage, Integer size, DepartmentDTO departmentDTO);

    /**
     * 新增科室信息
     * @param departmentDTO 科室信息 DTO
     * @return 新增结果
     */
    Result<?> add(DepartmentDTO departmentDTO);

    /**
     * 删除科室信息
     * @param id 科室 id
     * @return 删除结果
     */
    Result<?> delete(Integer id);

    /**
     * 更新科室信息
     * @param departmentDTO 科室信息 DTO
     * @return 更新结果
     */
    Result<?> update(DepartmentDTO departmentDTO);
}
