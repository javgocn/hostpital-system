package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.MedicalRecordDTO;
import cn.edu.just.hostpital.system.model.MedicalRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 病历信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface MedicalRecordService extends IService<MedicalRecord> {

    /**
     * 分页查询病例信息
     *
     * @param currentPage      当前页
     * @param page             每页大小
     * @param medicalRecordDTO 病例信息 DTO
     * @return 查询结果
     */
    Result<?> selectByPage(Integer currentPage, Integer size, MedicalRecordDTO medicalRecordDTO);

    /**
     * 添加病例信息
     *
     * @param medicalRecordDTO 病例信息 DTO
     * @return 添加结果
     */
    Result<?> addRecord(MedicalRecordDTO medicalRecordDTO);
}
