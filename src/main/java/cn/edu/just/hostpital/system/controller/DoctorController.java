package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.DoctorInfoDTO;
import cn.edu.just.hostpital.system.service.DoctorService;
import cn.edu.just.hostpital.system.validation.group.Save;
import cn.edu.just.hostpital.system.validation.group.Select;
import cn.edu.just.hostpital.system.validation.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 医生信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "医生信息 API", value = "DoctorController")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody @Validated DoctorInfoDTO doctorInfoDTO, HttpServletRequest request) {
        return doctorService.login(doctorInfoDTO, request);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return doctorService.logout(request, response);
    }

    @ApiOperation(value = "锁定账户")
    @PostMapping("/lock")
    public Result<?> lock(@RequestBody @Validated(Update.class) DoctorInfoDTO doctorInfoDTO) {
        return doctorService.lock(doctorInfoDTO);
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> selectByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) @Validated(Select.class) DoctorInfoDTO doctorInfoDTO) {
        return doctorService.selectByPage(currentPage, size, doctorInfoDTO);
    }

    @ApiOperation(value = "精确查询")
    @PostMapping("/getItem/{id}")
    public Result<?> getItem(@PathVariable @NotNull(message = "医生 id 不能为空") Integer id) {
        return doctorService.findById(id);
    }

    @ApiOperation(value = "新增医生")
    @PostMapping("/add")
    public Result<?> add(@RequestBody @Validated(Save.class) DoctorInfoDTO doctorInfoDTO) {
        return doctorService.add(doctorInfoDTO);
    }

    @ApiOperation(value = "删除医生")
    @PostMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") @NotNull(message = "医生 id 不能为空") Integer id) {
        return doctorService.delete(id);
    }

    @ApiOperation(value = "更新医生信息")
    @PostMapping("/update")
    public Result<?> update(@RequestBody @Validated(Update.class) DoctorInfoDTO doctorInfoDTO) {
        return doctorService.update(doctorInfoDTO);
    }

    @ApiOperation(value = "修改账户密码")
    @PostMapping("/reset/{oldPwd}/{newPwd}/{id}")
    public Result<?> resetPassword(@PathVariable("oldPwd") @NotNull(message = "原密码不能为空") String oldPwd,
                                   @PathVariable("newPwd") @NotNull(message = "新密码不能为空") String newPwd,
                                   @PathVariable("id") @NotNull(message = "医生 id不能为空") Integer id) {
        return doctorService.resetPassword(oldPwd, newPwd, id);
    }
}
