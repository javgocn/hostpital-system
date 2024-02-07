package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.MedicalRecordDTO;
import cn.edu.just.hostpital.system.dto.MedicationInfoDTO;
import cn.edu.just.hostpital.system.model.MedicationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 药品信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface MedicationInfoService extends IService<MedicationInfo> {

    /**
     * 分页查询
     *
     * @param currentPage       当前页
     * @param size              每页显示条数
     * @param medicationInfoDTO 查询条件
     * @return 查询结果
     */
    Result<?> selectByPage(Integer currentPage, Integer size, MedicationInfoDTO medicationInfoDTO);

    /**
     * 查询药品信息
     *
     * @param medicationInfoDTO 查询条件
     * @return 查询结果
     */
    Result<?> selectMedication(MedicationInfoDTO medicationInfoDTO);

    /**
     * 添加药品信息
     *
     * @param medicationInfoDTO 药品信息
     * @return 添加结果
     */
    Result<?> add(MedicationInfoDTO medicationInfoDTO);

    /**
     * 编辑药品信息
     *
     * @param medicationInfoDTO 药品信息
     * @return 编辑结果
     */
    Result<?> update(MedicationInfoDTO medicationInfoDTO);

    /**
     * 删除药品信息
     *
     * @param id 药品信息 id
     * @return 删除结果
     */
    Result<?> delete(Integer id);
}
