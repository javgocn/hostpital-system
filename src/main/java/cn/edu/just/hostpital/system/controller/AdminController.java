package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.AdminInfoDTO;
import cn.edu.just.hostpital.system.service.AdminInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 管理员信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "管理员信息 API", value = "AdminInfoController")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminInfoService adminInfoService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody AdminInfoDTO adminInfoDTO, HttpServletRequest request) {
        return adminInfoService.login(adminInfoDTO, request);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return adminInfoService.logout(request, response);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/repwd/{pwd}/{newpwd}/{id}")
    public Result<?> resetPwd(String pwd, String newpwd, Integer id) {
        return adminInfoService.resetPwd(pwd, newpwd, id);
    }

    @ApiOperation(value = "获取用户数量")
    @PostMapping("/getNum")
    public Result<?> getNum() {
        return adminInfoService.getNum();
    }

    @ApiOperation(value = "获取统计信息")
    @PostMapping("/getphoto")
    public Result<?> getPhoto() {
        return adminInfoService.getPhoto();
    }

}
