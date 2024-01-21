package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.req.UserInfoReq;
import cn.edu.just.hostpital.system.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "用户信息 API", value = "UserInfoController")
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserInfoReq userInfoReq) {
        return userInfoService.register(userInfoReq);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserInfoReq userInfoReq, HttpServletRequest request) {
        return userInfoService.login(userInfoReq, request);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return userInfoService.logout(request, response);
    }

    @ApiOperation(value = "分页获取用户信息")
    @PostMapping("/select/{currentPage}/{size}")
    Result<?> selectByPage(@PathVariable("currentPage") int currentPage, @PathVariable("size") int size, @RequestBody UserInfoReq userInfoReq) {
        return userInfoService.selectByPage(currentPage, size, userInfoReq);
    }

    @ApiOperation(value = "锁定用户")
    @PostMapping("/lock")
    Result<?> lock(@RequestBody UserInfoReq userInfoReq) {
        return userInfoService.lock(userInfoReq);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    Result<?> delete(@PathVariable("id") Integer id) {
        return userInfoService.deleteById(id);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    Result<?> update(@RequestBody UserInfoReq userInfoReq) {
        return userInfoService.update(userInfoReq);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/repwd/{pwd}/{newpwd}/{id}")
    Result<?> resetPwd(@PathVariable("pwd") String pwd, @PathVariable("newpwd") String newpwd, @PathVariable Integer id) {
        return userInfoService.resetPwdById(pwd, newpwd, id);
    }

    @ApiOperation(value = "获取用户数量")
    @PostMapping("/getUserNum")
    Result<?> getUserMenus() {
        return userInfoService.getUserMenus();
    }

}
