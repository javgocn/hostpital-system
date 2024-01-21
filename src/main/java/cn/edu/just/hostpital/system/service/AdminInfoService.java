package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.AdminInfo;
import cn.edu.just.hostpital.system.req.AdminInfoReq;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 管理员信息表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface AdminInfoService extends IService<AdminInfo> {

    /**
     * 管理员登录
     *
     * @param admin   管理员信息
     * @param request 请求
     * @return 登录结果
     */
    Result<?> login(AdminInfoReq adminInfoReq, HttpServletRequest request);

    /**
     * 修改密码
     *
     * @param pwd    原密码
     * @param newPwd 新密码
     * @param id     管理员id
     * @return 修改结果
     */
    Result<?> resetPwd(String pwd, String newPwd, Integer id);

    /**
     * 退出登录
     *
     * @param req 请求
     * @param res 响应
     * @return 退出结果
     */
    Result<?> logout(HttpServletRequest req, HttpServletResponse res);

    /**
     * 获取首页数据
     *
     * @return 首页数据
     */
    Result<?> getNum();

    /**
     * 获取统计图数据
     *
     * @return 统计图数据
     */
    Result<?> getPhoto();
}
