package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PositionInfoDTO;
import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.PrescriptionInfoMapper;
import cn.edu.just.hostpital.system.model.PositionInfo;
import cn.edu.just.hostpital.system.mapper.PositionInfoMapper;
import cn.edu.just.hostpital.system.service.PositionInfoService;
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
 * 职位信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class PositionInfoServiceImpl extends ServiceImpl<PositionInfoMapper, PositionInfo> implements PositionInfoService {

        @Resource
        private PositionInfoMapper positionInfoMapper;

        @Override
        public Result<?> selectByPage(Integer currentPage, Integer size, PositionInfoDTO positionInfoDTO) {
            IPage<PositionInfo> page = new Page<>(currentPage, size);
            IPage<PositionInfo> positionInfoIPage = null;
            QueryWrapper<PositionInfo> queryWrapper = new QueryWrapper<>();

            if (Objects.nonNull(positionInfoDTO)) {
                PositionInfo positionInfo = DataTransferUtil.shallowCopy(positionInfoDTO, PositionInfo.class);
                if (StringUtils.isNotBlank(positionInfo.getPositionName())) {
                    queryWrapper.like("position_name", positionInfo.getPositionName());
                }
            }
            queryWrapper.ne("status", StatusType.DELETED.getCode());
            queryWrapper.orderByDesc("create_time");
            positionInfoIPage = positionInfoMapper.selectPage(page, queryWrapper);
            return Result.success(positionInfoIPage);
        }

        @Override
        public Result<?> selectAll() {
            return Result.success(positionInfoMapper.selectList(null));
        }

        @Override
        public Result<?> add(PositionInfoDTO positionInfoDTO) {
            PositionInfo positionInfo = DataTransferUtil.shallowCopy(positionInfoDTO, PositionInfo.class);
            positionInfo.setCreateTime(new Date());
            positionInfoMapper.insert(positionInfo);
            return Result.success("新增成功");
        }

        @Override
        public Result<?> delete(Integer id) {
            positionInfoMapper.deleteById(id);
            return Result.success("删除成功");
        }

        @Override
        public Result<?> update(PositionInfoDTO positionInfoDTO) {
            PositionInfo positionInfo = DataTransferUtil.shallowCopy(positionInfoDTO, PositionInfo.class);
            positionInfo.setUpdateTime(new Date());
            positionInfoMapper.updateById(positionInfo);
            return Result.success("更新成功");
        }
}
