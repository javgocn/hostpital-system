package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.AnnouncementDTO;
import cn.edu.just.hostpital.system.service.AnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "公告 API", value = "AnnouncementController")
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Resource
    private AnnouncementService announcementService;

    @ApiOperation(value = "分页查询公告信息")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> select(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size,
                            @RequestBody(required = false) AnnouncementDTO announcementDTO) {
        return announcementService.selectByPage(currentPage, size, announcementDTO);
    }

    @ApiOperation(value = "新增公告信息")
    @PostMapping("/add/{name}")
    public Result<?> add(@PathVariable("name") String name, @RequestBody AnnouncementDTO announcementDTO) {
        return announcementService.add(name, announcementDTO);
    }

    @ApiOperation(value = "删除公告信息")
    @PostMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        return announcementService.delete(id);
    }
}
