package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.HospitalizationInfoDTO;
import cn.edu.just.hostpital.system.service.HospitalizationInfoService;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Select;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 住院信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "住院信息 API", value = "HospitalizationInfoController")
@RestController
@RequestMapping("/hospitalizationInfo")
public class HospitalizationInfoController {

    @Resource
    private HospitalizationInfoService hospitalizationInfoService;

    @ApiOperation(value = "分页查询住院信息")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> select(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size,
                            @RequestBody @Validated(Select.class) HospitalizationInfoDTO hospitalizationInfoDTO) {
        return hospitalizationInfoService.selectByPage(currentPage, size, hospitalizationInfoDTO);
    }

    @ApiOperation(value = "办理出院")
    @PostMapping("/outLive")
    public Result<?> outLive(@RequestBody @Validated(Update.class) HospitalizationInfoDTO hospitalizationInfoDTO) {
        return hospitalizationInfoService.outLive(hospitalizationInfoDTO);
    }

    @ApiOperation(value = "办理住院")
    @PostMapping("/addLive")
    public Result<?> addLive(@RequestBody @Validated(Save.class) HospitalizationInfoDTO hospitalizationInfoDTO) {
        return hospitalizationInfoService.addLive(hospitalizationInfoDTO);
    }
}
