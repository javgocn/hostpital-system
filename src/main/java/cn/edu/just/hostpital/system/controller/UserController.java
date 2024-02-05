package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserInfoDTO;
import cn.edu.just.hostpital.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "用户信息 API", value = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserInfoDTO UserInfoDTO) {
        return userService.register(UserInfoDTO);
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserInfoDTO UserInfoDTO, HttpServletRequest request) {
        return userService.login(UserInfoDTO, request);
    }

    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }

    @ApiOperation(value = "分页获取用户信息")
    @PostMapping("/select/{currentPage}/{size}")
    Result<?> selectByPage(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size, @RequestBody(required = false) @Validated UserInfoDTO userInfoDTO) {
        if (Objects.isNull(currentPage)) {
            currentPage = 1;
        }
        if (Objects.isNull(size)) {
            size = 50;
        }
        return userService.selectByPage(currentPage, size, userInfoDTO);
    }

    @ApiOperation(value = "锁定用户")
    @PostMapping("/lock")
    Result<?> lock(@RequestBody UserInfoDTO UserInfoDTO) {
        return userService.lock(UserInfoDTO);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    Result<?> delete(@PathVariable("id") Integer id) {
        return userService.deleteById(id);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/update")
    Result<?> update(@RequestBody UserInfoDTO UserInfoDTO) {
        return userService.update(UserInfoDTO);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/repwd/{pwd}/{newpwd}/{id}")
    Result<?> resetPwd(@PathVariable("pwd") String pwd, @PathVariable("newpwd") String newpwd, @PathVariable Integer id) {
        return userService.resetPwdById(pwd, newpwd, id);
    }

    @ApiOperation(value = "获取用户数量")
    @PostMapping("/getUserNum")
    Result<?> getUserMenus() {
        return userService.getUserMenus();
    }

}
