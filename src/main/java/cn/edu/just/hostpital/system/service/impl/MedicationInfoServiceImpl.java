package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.MedicalRecordDTO;
import cn.edu.just.hostpital.system.dto.MedicationInfoDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.model.MedicationInfo;
import cn.edu.just.hostpital.system.mapper.MedicationInfoMapper;
import cn.edu.just.hostpital.system.service.MedicationInfoService;
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
 * 药品信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class MedicationInfoServiceImpl extends ServiceImpl<MedicationInfoMapper, MedicationInfo> implements MedicationInfoService {

    @Resource
    private MedicationInfoMapper medicationInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, MedicationInfoDTO medicationInfoDTO) {
        IPage<MedicationInfo> page = new Page<>(currentPage, size);
        IPage<MedicationInfo> medicationInfoIPage = null;
        QueryWrapper<MedicationInfo> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(medicationInfoDTO)) {
            MedicationInfo medicationInfo = DataTransferUtil.shallowCopy(medicationInfoDTO, MedicationInfo.class);
            if ((Objects.nonNull(medicationInfo.getId()))) {
                queryWrapper.eq("id", medicationInfo.getId());
            }
            if (StringUtils.isNotBlank(medicationInfo.getMedicationName())) {
                queryWrapper.like("medication_name", medicationInfo.getMedicationName());
            }
            if (StringUtils.isNotBlank(medicationInfo.getMedicationCode())) {
                queryWrapper.like("medication_code", medicationInfo.getMedicationCode());
            }
        }
        queryWrapper.ne("status", StatusType.DELETED.getCode());
        queryWrapper.orderByDesc("create_time");
        medicationInfoIPage = medicationInfoMapper.selectPage(page, queryWrapper);
        return Result.success(medicationInfoIPage);
    }

    @Override
    public Result<?> selectMedication(MedicationInfoDTO medicationInfoDTO) {
        return null;
    }

    @Override
    public Result<?> add(MedicationInfoDTO medicationInfoDTO) {
        MedicationInfo medicationInfo = DataTransferUtil.shallowCopy(medicationInfoDTO, MedicationInfo.class);
        medicationInfo.setCreateTime(new Date());
        medicationInfo.setUpdateTime(new Date());
        try {
            medicationInfoMapper.insert(medicationInfo);
        } catch (Exception e) {
            return Result.fail("添加失败");
        }
        return Result.success("添加成功");
    }

    @Override
    public Result<?> update(MedicationInfoDTO medicationInfoDTO) {
        MedicationInfo medicationInfo = DataTransferUtil.shallowCopy(medicationInfoDTO, MedicationInfo.class);
        medicationInfo.setUpdateTime(new Date());
        try {
            medicationInfoMapper.updateById(medicationInfo);
        } catch (Exception e) {
            return Result.fail("更新失败");
        }
        return Result.success("更新成功");
    }

    @Override
    public Result<?> delete(Integer id) {
        int count = medicationInfoMapper.deleteById(id);
        return count == 1 ? Result.success("删除成功") : Result.fail("删除失败");
    }
}
