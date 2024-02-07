package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.model.PrescriptionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 处方信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface PrescriptionInfoService extends IService<PrescriptionInfo> {

    /**
     * 分页查询处方信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @param prescriptionInfoDTO 厨房信息 DTO
     * @return 查询结果
     */
    Result<?> selectByPage(Integer currentPage, Integer size, PrescriptionInfoDTO prescriptionInfoDTO);

    /**
     * 开具处方
     * @param prescriptionInfoDTO 处方信息 DTO
     * @return 开方结果
     */
    Result<?> addPrescript(PrescriptionInfoDTO prescriptionInfoDTO);

    /**
     * 删除处方
     * @param id 处方 id
     * @return 删除结果
     */
    Result<?> deletePrescript(Integer id);
}
