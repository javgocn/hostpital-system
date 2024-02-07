package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.PrescriptionDrug;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 处方药品关联表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface PrescriptionDrugService extends IService<PrescriptionDrug> {

    /**
     * 分页查询处方药物
     * @param currentPage 当前页
     * @param size 每页大小
     * @param id id
     * @return 查询结果
     */
    Result<?> selectByPage(Integer currentPage, Integer size, Integer id);

    /**
     * 获取总价
     * @param id 处方 id
     * @return 总价
     */
    Result<?> getTotalPrice(Integer id);

    /**
     * 配药
     * @param prescId 处方 id
     * @param drugId 药品 id
     * @param num 数量
     * @return 配药结果
     */
    Result<?> newPresc(Integer prescId, Integer drugId, Integer num);

    /**
     * 删除药物
     * @param id 药物 id
     * @return 删除结果
     */
    Result<?> deleteDrug(Integer id);
}
