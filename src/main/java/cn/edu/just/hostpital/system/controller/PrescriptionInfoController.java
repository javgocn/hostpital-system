package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.service.PrescriptionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 处方信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "处方信息 API", value = "PrescriptionInfoController")
@RestController
@RequestMapping("/prescriptionInfo")
public class PrescriptionInfoController {

    @Resource
    private PrescriptionInfoService prescriptionInfoService;

    @ApiOperation(value = "分页查询处方信息")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> select(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size,
                            @RequestBody PrescriptionInfoDTO prescriptionInfoDTO) {
        return prescriptionInfoService.selectByPage(currentPage, size, prescriptionInfoDTO);
    }

    @ApiOperation(value = "添加处方信息")
    @PostMapping("/createPresc")
    public Result<?> createPresc(@RequestBody PrescriptionInfoDTO prescriptionInfoDTO) {
        return prescriptionInfoService.addPrescript(prescriptionInfoDTO);
    }

    @ApiOperation(value = "删除处方信息")
    @PostMapping("/deletePresc/{id}")
    public Result<?> deletePresc(@PathVariable("id") Integer id) {
        return prescriptionInfoService.deletePrescript(id);
    }
}
