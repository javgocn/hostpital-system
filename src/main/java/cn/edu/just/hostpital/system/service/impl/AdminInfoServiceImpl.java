package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.AdminInfo;
import cn.edu.just.hostpital.system.mapper.AdminInfoMapper;
import cn.edu.just.hostpital.system.service.AdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 管理员信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {

    @Override
    public Result<?> login(AdminInfo admin, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result<?> resetPwd(String pwd, String newPwd, Integer id) {
        return null;
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }

    @Override
    public Result<?> getNum() {
        return null;
    }

    @Override
    public Result<?> getPhoto() {
        return null;
    }
}
