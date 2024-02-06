package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.AnnouncementDTO;
import cn.edu.just.hostpital.system.model.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface AnnouncementService extends IService<Announcement> {

    /**
     * 分页查询公告信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @return 公告信息列表
     */
    Result<?> selectByPage(Integer currentPage, Integer size, AnnouncementDTO announcementDTO);

    /**
     * 新增公告信息
     * @param name 公告名称
     * @param announcementDTO 公告信息 DTO
     * @return 新增结果
     */
    Result<?> add(String name, AnnouncementDTO announcementDTO);

    /**
     * 删除公告信息
     * @param id 公告 id
     * @return 删除结果
     */
    Result<?> delete(Integer id);
}
