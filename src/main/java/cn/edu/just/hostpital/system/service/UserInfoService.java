package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.UserInfo;
import cn.edu.just.hostpital.system.req.UserInfoReq;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 注册用户
     * @param user 用户信息
     * @return 注册结果
     */
     Result<?> register(UserInfoReq user);

    /**
     * 登录用户
     * @param user 用户信息
     * @param request 请求
     * @return 登录结果
     */
    Result<?> login(UserInfoReq user, HttpServletRequest request);

    /**
     * 获取用户
     * @param currentPage 当前页
     * @param size 每页大小
     * @param user 用户信息
     * @return 用户列表
     */
    Result<?> selectByPage(int currentPage, int size, UserInfoReq user);

    /**
     * 锁定用户
     * @param user 用户信息
     * @return 锁定结果
     */
    Result<?> lock(UserInfoReq user);

    /**
     * 删除用户
     * @param id 用户id
     * @return 删除结果
     */
    Result<?> deleteById(Integer id);

    /**
     * 获得用户信息
     * @param id 用户id
     * @return 用户信息
     */
    Result<?> getItemById(Integer id);

    /**
     * 更新用户数据
     * @param user 用户信息
     * @return 更新结果
     */
    Result<?> update(UserInfoReq user);

    /**
     * 修改密码
     * @param pwd 原密码
     * @param newPwd 新密码
     * @param id 用户id
     * @return 修改结果
     */
    Result<?> resetPwdById(String pwd, String newPwd, Integer id);

    /**
     * 退出登录
     * @param req 请求
     * @param res 响应
     * @return 退出结果
     */
    Result<?> logout(HttpServletRequest req, HttpServletResponse res);

    /**
     * 获取用户首页内容
     * @return 首页内容
     */
    Result<?> getUserMenus();
}
