package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.MedicalRecordDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.model.MedicalRecord;
import cn.edu.just.hostpital.system.mapper.MedicalRecordMapper;
import cn.edu.just.hostpital.system.service.MedicalRecordService;
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
 * 病历信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class MedicalRecordServiceImpl extends ServiceImpl<MedicalRecordMapper, MedicalRecord> implements MedicalRecordService {

    @Resource
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, MedicalRecordDTO medicalRecordDTO) {
        IPage<MedicalRecord> page1 = new Page<>(currentPage, size);
        IPage<MedicalRecord> medicalRecordIPage = null;
        QueryWrapper<MedicalRecord> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(medicalRecordDTO)) {
            MedicalRecord medicalRecord = DataTransferUtil.shallowCopy(medicalRecordDTO, MedicalRecord.class);
            if (Objects.nonNull(medicalRecord.getRecordDate())) {
                queryWrapper.eq("record_date", medicalRecord.getRecordDate());
            }
            if (Objects.nonNull(medicalRecord.getPatientId())) {
                queryWrapper.eq("patient_id", medicalRecord.getPatientId());
            }
        }
        queryWrapper.ne("status", StatusType.DELETED.getCode());
        queryWrapper.orderByDesc("create_time");
        medicalRecordIPage = medicalRecordMapper.selectPage(page1, queryWrapper);
        return Result.success(medicalRecordIPage);
    }

    @Override
    public Result<?> addRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = DataTransferUtil.shallowCopy(medicalRecordDTO, MedicalRecord.class);
        medicalRecord.setRecordDate(new Date());
        medicalRecord.setCreateTime(new Date());
        int count = medicalRecordMapper.insert(medicalRecord);
        return count == 1 ? Result.success("病历上传成功") : Result.fail("病历上传失败");
    }
}
