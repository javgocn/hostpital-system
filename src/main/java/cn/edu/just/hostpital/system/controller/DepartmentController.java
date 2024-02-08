package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.DepartmentDTO;
import cn.edu.just.hostpital.system.service.DepartmentService;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 科室信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "科室信息 API", value = "DepartmentController")
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @ApiOperation(value = "分页查询科室信息")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> seleteByPage(@PathVariable("currentPage") Integer currentPage,
                                  @PathVariable("size") Integer size,
                                  @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.selectByPage(currentPage, size, departmentDTO);
    }

    @ApiOperation(value = "查询所有科室信息")
    @PostMapping("/selectAll")
    public Result<?> selectAll() {
        return departmentService.selectAll();
    }

    @ApiOperation(value = "新增科室信息")
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Validated(Save.class) DepartmentDTO departmentDTO) {
        return departmentService.add(departmentDTO);
    }

    @ApiOperation(value = "删除科室信息")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        return departmentService.delete(id);
    }

    @ApiOperation(value = "修改科室信息")
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Validated(Update.class) DepartmentDTO departmentDTO) {
        return departmentService.update(departmentDTO);
    }
}
