package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.constans.UserConstants;
import cn.edu.just.hostpital.system.dto.UserInfoDTO;
import cn.edu.just.hostpital.system.model.UserInfo;
import cn.edu.just.hostpital.system.mapper.UserInfoMapper;
import cn.edu.just.hostpital.system.req.UserInfoReq;
import cn.edu.just.hostpital.system.service.UserInfoService;
import cn.edu.just.hostpital.system.utils.DataTransferUtils;
import cn.edu.just.hostpital.system.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<?> register(UserInfoReq user) {
        Result<?> result = checkUserParam(user);
        if (!result.isSuccess()) {
            return result;
        }

        UserInfo userInfo = DataTransferUtils.shallowCopy(user, UserInfo.class);

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userInfo.getUsername());
        Long count = userInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.failed("用户名已存在,请重新输入");
        }

        String finalPwd = MD5Util.md5(userInfo.getUsername(), userInfo.getPassword(), UserConstants.USER_SLAT, String.valueOf(System.currentTimeMillis()));
        userInfo.setPassword(finalPwd);

        userInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        int insert = userInfoMapper.insert(userInfo);
        if (insert == 0) {
            Result.failed("注册失败");
        }

        UserInfoDTO userInfoDTO = DataTransferUtils.shallowCopy(userInfo, UserInfoDTO.class);
        return Result.success(userInfoDTO, "注册成功");
    }

    private Result<?> checkUserParam(UserInfoReq user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.failed("用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return Result.failed("密码不能为空");
        }
        return Result.success(null);
    }

    @Override
    public Result<?> login(UserInfoReq user, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result<?> selectByPage(int currentPage, int size, UserInfoReq user) {
        return null;
    }

    @Override
    public Result<?> lock(UserInfoReq user) {
        return null;
    }

    @Override
    public Result<?> deleteById(Integer id) {
        return null;
    }

    @Override
    public Result<?> getItemById(Integer id) {
        return null;
    }

    @Override
    public Result<?> update(UserInfoReq user) {
        return null;
    }

    @Override
    public Result<?> resetPwdById(String pwd, String newPwd, Integer id) {
        return null;
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }

    @Override
    public Result<?> getUserMenus() {
        return null;
    }
}
