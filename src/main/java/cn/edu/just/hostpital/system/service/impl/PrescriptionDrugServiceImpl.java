package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.mapper.MedicationInfoMapper;
import cn.edu.just.hostpital.system.mapper.PrescriptionInfoMapper;
import cn.edu.just.hostpital.system.model.MedicationInfo;
import cn.edu.just.hostpital.system.model.PrescriptionDrug;
import cn.edu.just.hostpital.system.mapper.PrescriptionDrugMapper;
import cn.edu.just.hostpital.system.model.PrescriptionInfo;
import cn.edu.just.hostpital.system.service.PrescriptionDrugService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 处方药品关联表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class PrescriptionDrugServiceImpl extends ServiceImpl<PrescriptionDrugMapper, PrescriptionDrug> implements PrescriptionDrugService {

    @Resource
    private PrescriptionDrugMapper prescriptionDrugMapper;

    @Resource
    private PrescriptionInfoMapper prescriptionInfoMapper;

    @Resource
    private MedicationInfoMapper medicationInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, Integer id) {
        IPage<PrescriptionDrug> page = new Page<>(currentPage, size);
        IPage<PrescriptionDrug> prescriptionDrugIPage = null;
        QueryWrapper<PrescriptionDrug> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prescriptionId", id);
        prescriptionDrugIPage = prescriptionDrugMapper.selectPage(page, queryWrapper);
        return Result.success(prescriptionDrugIPage);
    }

    @Override
    public Result<?> getTotalPrice(Integer id) {
        QueryWrapper<PrescriptionDrug> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("prescriptionId", id);
        List<PrescriptionDrug> prescriptionDrugs = prescriptionDrugMapper.selectList(queryWrapper);
        BigDecimal totalPrice = new BigDecimal(0);
        prescriptionDrugs.forEach(prescriptionDrug -> totalPrice.add(prescriptionDrug.getTotalPrice()));
        return Result.success(totalPrice);
    }

    @Override
    public Result<?> newPresc(Integer prescId, Integer drugId, Integer num) {
        PrescriptionInfo prescriptionInfo = prescriptionInfoMapper.selectById(prescId);
        MedicationInfo medicationInfo = medicationInfoMapper.selectById(drugId);
        if (num > medicationInfo.getStock()) {
            return Result.fail("库存不足");
        }

        try {
            medicationInfo.setUpdateTime(new Date());
            medicationInfo.setStock(medicationInfo.getStock() - num);
            medicationInfoMapper.updateById(medicationInfo);

            PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
            prescriptionDrug.setPrescriptionId(prescId);
            prescriptionDrug.setMedicationId(drugId);
            prescriptionDrug.setMedicationName(medicationInfo.getMedicationName());
            prescriptionDrug.setManufacturer(medicationInfo.getManufacturer());
            prescriptionDrug.setTotalPrice(medicationInfo.getPrice().multiply(new BigDecimal(num)));
            prescriptionDrug.setUnit(medicationInfo.getUnit());
            prescriptionDrug.setQuantity(num);
            prescriptionDrugMapper.insert(prescriptionDrug);

            prescriptionInfo.setTotalPrice(prescriptionInfo.getTotalPrice().add(prescriptionDrug.getTotalPrice()));
            prescriptionInfoMapper.updateById(prescriptionInfo);
        } catch (Exception e) {
            return Result.fail("配药失败");
        }
        return Result.success("配药成功");
    }

    @Override
    public Result<?> deleteDrug(Integer id) {
        PrescriptionDrug prescriptionDrug = prescriptionDrugMapper.selectById(id);
        PrescriptionInfo prescriptionInfo = prescriptionInfoMapper.selectById(prescriptionDrug.getPrescriptionId());
        prescriptionInfo.setTotalPrice(prescriptionInfo.getTotalPrice().subtract(prescriptionDrug.getTotalPrice()));
        prescriptionInfoMapper.updateById(prescriptionInfo);

        MedicationInfo medicationInfo = medicationInfoMapper.selectById(prescriptionDrug.getMedicationId());
        medicationInfo.setStock(medicationInfo.getStock() + prescriptionDrug.getQuantity());
        medicationInfoMapper.updateById(medicationInfo);

        prescriptionDrugMapper.deleteById(id);
        return Result.success("删除成功");
    }
}
