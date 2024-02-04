package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.AdminInfoDTO;
import cn.edu.just.hostpital.system.model.AdminInfo;
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
     */
    Result<?> login(AdminInfoDTO adminInfoDTO, HttpServletRequest request);

    /**
     * 添加管理员
     *
     * @param adminInfoDTO 管理员信息
     * @return 添加结果
     */
    Result<?> add(AdminInfoDTO adminInfoDTO);

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
