package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.HospitalizationInfoDTO;
import cn.edu.just.hostpital.system.enums.BedType;
import cn.edu.just.hostpital.system.enums.HospitalizationType;
import cn.edu.just.hostpital.system.enums.RoomType;
import cn.edu.just.hostpital.system.mapper.BedMapper;
import cn.edu.just.hostpital.system.mapper.RoomMapper;
import cn.edu.just.hostpital.system.model.Bed;
import cn.edu.just.hostpital.system.model.HospitalizationInfo;
import cn.edu.just.hostpital.system.mapper.HospitalizationInfoMapper;
import cn.edu.just.hostpital.system.model.Room;
import cn.edu.just.hostpital.system.service.HospitalizationInfoService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 住院信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class HospitalizationInfoServiceImpl extends ServiceImpl<HospitalizationInfoMapper, HospitalizationInfo> implements HospitalizationInfoService {

    @Resource
    private HospitalizationInfoMapper hospitalizationInfoMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private BedMapper bedMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, HospitalizationInfoDTO hospitalizationInfoDTO) {
        IPage<HospitalizationInfo> page = new Page<>(currentPage, size);
        IPage<HospitalizationInfo> hospitalizationInfoIPage = null;
        QueryWrapper<HospitalizationInfo> queryWrapper = new QueryWrapper<>();

        if (Objects.nonNull(hospitalizationInfoDTO)) {
            HospitalizationInfo hospitalizationInfo = DataTransferUtil.shallowCopy(hospitalizationInfoDTO, HospitalizationInfo.class);
            if (StringUtils.isNotBlank(hospitalizationInfo.getPatientName())) {
                queryWrapper.like("patient_name", hospitalizationInfo.getPatientName());
            }
            if (Objects.nonNull(hospitalizationInfo.getStatus())) {
                queryWrapper.eq("status", hospitalizationInfo.getStatus());
            }
            if (Objects.nonNull(hospitalizationInfo.getPatientId())) {
                queryWrapper.eq("patient_id", hospitalizationInfo.getPatientId());
            }
        }
        queryWrapper.ne("status", HospitalizationType.DELETED.getCode());
        queryWrapper.orderByDesc("create_time");

        hospitalizationInfoIPage = hospitalizationInfoMapper.selectPage(page, queryWrapper);
        return Result.success(hospitalizationInfoIPage);
    }

    @Override
    @Transactional
    public Result<?> addLive(HospitalizationInfoDTO hospitalizationInfoDTO) {
        HospitalizationInfo hospitalizationInfo = DataTransferUtil.shallowCopy(hospitalizationInfoDTO, HospitalizationInfo.class);
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("id", hospitalizationInfo.getRoomId());
        Room room = roomMapper.selectOne(roomQueryWrapper);
        if (Objects.equals(room.getStatus(), RoomType.FULL.getCode())) {
            return Result.fail("该病房已住满，请更换其他病房");
        } else if (Objects.equals(room.getStatus(), RoomType.REPAIR.getCode())) {
            return Result.fail("该病房维修中，暂不提供使用");
        }
        room.setBedCount(room.getBedCount() - 1);
        if (room.getBedCount() == 0) {
            room.setStatus(RoomType.FULL.getCode());
        }
        roomMapper.updateById(room);

        QueryWrapper<Bed> bedQueryWrapper = new QueryWrapper<>();
        bedQueryWrapper.eq("id", hospitalizationInfo.getBedId());
        Bed bed = bedMapper.selectOne(bedQueryWrapper);
        if (!Objects.equals(bed.getStatus(), BedType.FREE.getCode())) {
            return Result.fail("床位暂时不可用，请重新选择");
        }
        bed.setStatus(BedType.OCCUPY.getCode());
        bedMapper.updateById(bed);

        int days = (int) ((hospitalizationInfo.getDischargeDate().getTime() - hospitalizationInfo.getAdmissionDate().getTime()) / 1000 * 3600 * 24);
        hospitalizationInfo.setTotalCost(bed.getPrice().multiply(new BigDecimal(days)));
        hospitalizationInfo.setCreateTime(new Date());

        hospitalizationInfoMapper.insert(hospitalizationInfo);
        return Result.success("住院办理成功");
    }

    @Override
    @Transactional
    public Result<?> outLive(HospitalizationInfoDTO hospitalizationInfoDTO) {
        HospitalizationInfo hospitalizationInfo = DataTransferUtil.shallowCopy(hospitalizationInfoDTO, HospitalizationInfo.class);
        Date now = new Date();
        Date endDate = hospitalizationInfo.getDischargeDate();
        if (now.compareTo(endDate) < 0) {
            return Result.fail("未到出院时间");
        } else if (Objects.equals(hospitalizationInfo.getStatus(), HospitalizationType.DISCHARGE.getCode())) {
            return Result.fail("该患者已出院");
        }

        hospitalizationInfo.setStatus(HospitalizationType.DISCHARGE.getCode());
        hospitalizationInfo.setUpdateTime(new Date());
        hospitalizationInfoMapper.updateById(hospitalizationInfo);

        Room room = roomMapper.selectById(hospitalizationInfo.getRoomId());
        room.setBedCount(room.getBedCount() + 1);
        roomMapper.updateById(room);

        Bed bed = bedMapper.selectById(hospitalizationInfo.getBedId());
        bed.setStatus(BedType.FREE.getCode());
        bedMapper.updateById(bed);

        return Result.success("出院办理成功");
    }
}
