package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.enums.PrescriptionType;
import cn.edu.just.hostpital.system.model.PrescriptionInfo;
import cn.edu.just.hostpital.system.mapper.PrescriptionInfoMapper;
import cn.edu.just.hostpital.system.service.PrescriptionInfoService;
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
 * 处方信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class PrescriptionInfoServiceImpl extends ServiceImpl<PrescriptionInfoMapper, PrescriptionInfo> implements PrescriptionInfoService {

    @Resource
    private PrescriptionInfoMapper prescriptionInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, PrescriptionInfoDTO prescriptionInfoDTO) {
        IPage<PrescriptionInfo> page = new Page<>(currentPage, size);
        IPage<PrescriptionInfo> prescriptionInfoIPage = null;
        QueryWrapper<PrescriptionInfo> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(prescriptionInfoDTO)) {
            PrescriptionInfo prescriptionInfo = DataTransferUtil.shallowCopy(prescriptionInfoDTO, PrescriptionInfo.class);
            if (StringUtils.isNotBlank(prescriptionInfo.getUsername())) {
                queryWrapper.like("username", prescriptionInfo.getUsername());
            }
            if (Objects.nonNull(prescriptionInfo.getUserId())) {
                queryWrapper.eq("user_id", prescriptionInfo.getUserId());
            }
            if (Objects.nonNull(prescriptionInfo.getDoctorId())) {
                queryWrapper.eq("doctor_id", prescriptionInfo.getDoctorId());
            }
            if (Objects.nonNull(prescriptionInfo.getCreateDate())) {
                queryWrapper.eq("create_date", prescriptionInfo.getCreateDate());
            }
        }
        queryWrapper.ne("status", PrescriptionType.DELETED.getCode());
        queryWrapper.orderByDesc("create_date");
        prescriptionInfoIPage = prescriptionInfoMapper.selectPage(page, queryWrapper);
        return Result.success(prescriptionInfoIPage);
    }

    @Override
    public Result<?> addPrescript(PrescriptionInfoDTO prescriptionInfoDTO) {
        PrescriptionInfo prescriptionInfo = DataTransferUtil.shallowCopy(prescriptionInfoDTO, PrescriptionInfo.class);
        prescriptionInfo.setCreateDate(new Date());
        prescriptionInfoMapper.insert(prescriptionInfo);
        return Result.success("处方开具成功");
    }

    @Override
    public Result<?> deletePrescript(Integer id) {
        try {
            prescriptionInfoMapper.deleteById(id);
        } catch (Exception e) {
            return Result.fail("该处方含有配药信息，无法删除");
        }
        return Result.success("该处方已删除");
    }
}
