package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PositionInfoDTO;
import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.model.PositionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 职位信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface PositionInfoService extends IService<PositionInfo> {

    /**
     * 分页查询职位信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @param positionInfoDTO 职位信息 DTO
     * @return 职位列表
     */
    Result<?> selectByPage(Integer currentPage, Integer size, PositionInfoDTO positionInfoDTO);

    /**
     * 查询所有职位信息
     * @return 职位列表
     */
    Result<?> selectAll();

    /**
     * 新增职位信息
     * @param positionInfoDTO 职位信息 DTO
     * @return 新增结果
     */
    Result<?> add(PositionInfoDTO positionInfoDTO);

    /**
     * 删除职位信息
     * @param id 职位 id
     * @return 删除结果
     */
    Result<?> delete(Integer id);

    /**
     * 更新职位信息
     * @param positionInfoDTO 职位信息 DTO
     * @return 更新结果
     */
    Result<?> update(PositionInfoDTO positionInfoDTO);
}
