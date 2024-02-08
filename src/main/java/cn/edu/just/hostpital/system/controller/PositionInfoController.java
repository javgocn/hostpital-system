package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PositionInfoDTO;
import cn.edu.just.hostpital.system.service.PositionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 职位信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "职位信息管理 API", value = "PositionInfoController")
@RestController
@RequestMapping("/position")
public class PositionInfoController {

    @Resource
    private PositionInfoService positionInfoService;

    @ApiOperation("分页查询职位信息")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> selectByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size,
                                  @RequestBody PositionInfoDTO positionInfoDTO) {
        return positionInfoService.selectByPage(currentPage, size, positionInfoDTO);
    }

    @ApiOperation("查询所有职位信息")
    @PostMapping("/selectAll")
    public Result<?> selectAll() {
        return positionInfoService.selectAll();
    }

    @ApiOperation("新增职位信息")
    @PostMapping("/add")
    public Result<?> add(@RequestBody PositionInfoDTO positionInfoDTO) {
        return positionInfoService.add(positionInfoDTO);
    }

    @ApiOperation("删除职位信息")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        return positionInfoService.delete(id);
    }

    @ApiOperation("更新职位信息")
    @PostMapping("/update")
    public Result<?> update(@RequestBody PositionInfoDTO positionInfoDTO) {
        return positionInfoService.update(positionInfoDTO);
    }
}
