package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.MedicationInfoDTO;
import cn.edu.just.hostpital.system.service.MedicationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 药品信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "药品信息 API", value = "MedicationInfoController")
@RestController
@RequestMapping("/drug")
public class MedicationInfoController {

    @Resource
    private MedicationInfoService medicationInfoService;

    @ApiOperation("分页查询")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> select(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size,
                            @RequestBody MedicationInfoDTO medicationInfoDTO) {
        return medicationInfoService.selectByPage(currentPage, size, medicationInfoDTO);
    }

    @ApiOperation("添加药品信息")
    @PostMapping("/add")
    public Result<?> add(@RequestBody MedicationInfoDTO medicationInfoDTO) {
        return medicationInfoService.add(medicationInfoDTO);
    }

    @ApiOperation("编辑药品信息")
    @PostMapping("/update")
    public Result<?> update(@RequestBody MedicationInfoDTO medicationInfoDTO) {
        return medicationInfoService.update(medicationInfoDTO);
    }

    @ApiOperation("删除药品信息")
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        return medicationInfoService.delete(id);
    }

}
