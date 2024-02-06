package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.AnnouncementDTO;
import cn.edu.just.hostpital.system.model.Announcement;
import cn.edu.just.hostpital.system.mapper.AnnouncementMapper;
import cn.edu.just.hostpital.system.service.AnnouncementService;
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
 * 公告表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Resource
    private AnnouncementMapper announcementMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size, AnnouncementDTO announcementDTO) {
        IPage<Announcement> page = new Page<>(currentPage, size);
        IPage<Announcement> announcementIPage = null;
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(announcementDTO)) {
            Announcement announcement = DataTransferUtil.shallowCopy(announcementDTO, Announcement.class);
            if (StringUtils.isNotBlank(announcement.getSubject())) {
                queryWrapper.like("subject", announcement.getSubject());
            }
        }
        queryWrapper.orderByDesc("create_time");
        announcementIPage = announcementMapper.selectPage(page, queryWrapper);
        return Result.success(announcementIPage);
    }

    @Override
    public Result<?> add(String name, AnnouncementDTO announcementDTO) {
        Announcement announcement = DataTransferUtil.shallowCopy(announcementDTO, Announcement.class);
        announcement.setCreateTime(new Date());
        announcement.setAuthor(name);
        int count = announcementMapper.insert(announcement);
        return count == 1 ? Result.success("发布成功") : Result.fail("发布失败");
    }

    @Override
    public Result<?> delete(Integer id) {
        int count = announcementMapper.deleteById(id);
        return count == 1 ? Result.success("删除成功") : Result.fail("删除失败");
    }
}
