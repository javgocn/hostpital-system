package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.HospitalizationInfoDTO;
import cn.edu.just.hostpital.system.model.HospitalizationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 住院信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface HospitalizationInfoService extends IService<HospitalizationInfo> {

    /**
     * 分页查询住院信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @param hospitalizationInfoDTO 住院信息 DTO
     * @return 查询结果
     */
    Result<?> selectByPage(Integer currentPage, Integer size, HospitalizationInfoDTO hospitalizationInfoDTO);

    /**
     * 添加住院信息
     * @param hospitalizationInfoDTO 住院信息 DTO
     * @return 添加结果
     */
    Result<?> addLive(HospitalizationInfoDTO hospitalizationInfoDTO);

    /**
     * 办理出院
     * @param hospitalizationInfoDTO 住院信息 DTO
     * @return 办理结果
     */
    Result<?> outLive(HospitalizationInfoDTO hospitalizationInfoDTO);
}
