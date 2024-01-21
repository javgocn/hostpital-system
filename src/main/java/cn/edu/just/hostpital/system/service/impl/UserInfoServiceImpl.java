package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.constans.UserConstants;
import cn.edu.just.hostpital.system.dto.UserInfoDTO;
import cn.edu.just.hostpital.system.enums.AnnouncementType;
import cn.edu.just.hostpital.system.enums.BedType;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.*;
import cn.edu.just.hostpital.system.model.*;
import cn.edu.just.hostpital.system.req.UserInfoReq;
import cn.edu.just.hostpital.system.service.UserInfoService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import cn.edu.just.hostpital.system.utils.MD5Util;
import cn.edu.just.hostpital.system.vo.UserNumVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private BedMapper bedMapper;

    @Resource
    private AnnouncementMapper announcementMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Result<?> register(UserInfoReq user) {
        Result<?> Result = checkUserParam(user);
        if (!Result.isSuccess()) {
            return Result;
        }

        UserInfo userInfo = DataTransferUtil.shallowCopy(user, UserInfo.class);

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userInfo.getUsername());
        Long count = userInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.fail("用户名已存在,请重新输入");
        }

        String finalPwd = MD5Util.md5(userInfo.getPassword(), UserConstants.USER_SLAT);
        userInfo.setPassword(finalPwd);

        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());

        int insert = userInfoMapper.insert(userInfo);
        if (insert == 0) {
            Result.fail("注册失败");
        }

        UserInfoDTO userInfoDTO = DataTransferUtil.shallowCopy(userInfo, UserInfoDTO.class);
        return Result.success(userInfoDTO);
    }

    private Result<?> checkUserParam(UserInfoReq user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.fail("用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return Result.fail("密码不能为空");
        }
        return Result.success(null);
    }

    @Override
    public Result<?> login(UserInfoReq user, HttpServletRequest request) {
        checkUserParam(user);
        UserInfo userInfo = DataTransferUtil.shallowCopy(user, UserInfo.class);
        String md5Pwd = MD5Util.md5(userInfo.getPassword(), UserConstants.USER_SLAT);

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userInfo.getUsername());
        queryWrapper.eq("password", md5Pwd);
        userInfo = userInfoMapper.selectOne(queryWrapper);

        if (Objects.isNull(userInfo)) {
            return Result.fail("登录失败，用户名或密码错误");
        }
        if (Objects.equals(userInfo.getStatus(), StatusType.DISABLE.getCode())) {
            return Result.fail("登录失败，用户已被锁定");
        }
        if (Objects.equals(userInfo.getStatus(), StatusType.DELETED.getCode())) {
            return Result.fail("登录失败，用户已被删除");
        }

        UserInfoDTO userInfoDTO = DataTransferUtil.shallowCopy(userInfo, UserInfoDTO.class);
        request.getSession().setAttribute("user", userInfoDTO.getName());

        return Result.success(userInfoDTO);
    }

    @Override
    public Result<?> selectByPage(int currentPage, int size, UserInfoReq user) {
        UserInfo userInfo = DataTransferUtil.shallowCopy(user, UserInfo.class);
        IPage<UserInfo> page = new Page<>(currentPage, size);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        IPage<UserInfo> userInfoIPage = null;

        if (!userInfo.getUsername().equals("") && userInfo.getName().equals("") && userInfo.getIdCard().equals("")) {
            queryWrapper.like("username", userInfo.getUsername());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (userInfo.getUsername().equals("") && !user.getName().equals("") && userInfo.getIdCard().equals("")) {
            queryWrapper.like("name", userInfo.getName());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (userInfo.getUsername().equals("") && user.getName().equals("") && !userInfo.getIdCard().equals("")) {
            queryWrapper.like("id_card", userInfo.getIdCard());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (!userInfo.getUsername().equals("") && !user.getName().equals("") && userInfo.getIdCard().equals("")) {
            queryWrapper.like("username", userInfo.getUsername());
            queryWrapper.like("name", userInfo.getName());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (!userInfo.getUsername().equals("") && userInfo.getName().equals("") && !userInfo.getIdCard().equals("")) {
            queryWrapper.like("username", userInfo.getUsername());
            queryWrapper.like("id_card", userInfo.getIdCard());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (userInfo.getUsername().equals("") && !userInfo.getName().equals("") && !userInfo.getIdCard().equals("")) {
            queryWrapper.like("name", userInfo.getName());
            queryWrapper.like("id_card", userInfo.getIdCard());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else if (!userInfo.getUsername().equals("") && !user.getName().equals("") && !userInfo.getIdCard().equals("")) {
            queryWrapper.like("username", userInfo.getUsername());
            queryWrapper.like("name", userInfo.getName());
            queryWrapper.like("id_card", userInfo.getIdCard());
            userInfoIPage = userInfoMapper.selectPage(page, queryWrapper);
        } else {
            userInfoIPage = userInfoMapper.selectPage(page, null);
        }

        IPage<UserInfoDTO> userInfoDTOIPage = DataTransferUtil.shallowCopy(userInfoIPage, IPage.class);
        return Result.success(userInfoDTOIPage);
    }

    @Override
    public Result<?> lock(UserInfoReq user) {
        UserInfo userInfo = DataTransferUtil.shallowCopy(user, UserInfo.class);
        if (Objects.equals(userInfo.getStatus(), StatusType.DISABLE.getCode())) {
            userInfo.setStatus(0);
            userInfoMapper.updateById(userInfo);
            return Result.success("解锁成功");
        } else {
            userInfo.setStatus(1);
            userInfoMapper.updateById(userInfo);
            return Result.success("锁定成功");
        }
    }

    @Override
    public Result<?> deleteById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        userInfo.setStatus(StatusType.DELETED.getCode());
        userInfoMapper.updateById(userInfo);
        return Result.success("删除成功");
    }

    @Override
    public Result<?> getItemById(Integer id) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        UserInfoDTO userInfoDTO = DataTransferUtil.shallowCopy(userInfo, UserInfoDTO.class);
        return Result.success(userInfoDTO);
    }

    @Override
    public Result<?> update(UserInfoReq user) {
        UserInfo userInfo = DataTransferUtil.shallowCopy(user, UserInfo.class);
        userInfo.setUpdateTime(new Date());
        userInfoMapper.updateById(userInfo);
        return Result.success("更新成功");
    }

    @Override
    public Result<?> resetPwdById(String pwd, String newPwd, Integer id) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        String md5Pwd = MD5Util.md5(pwd, UserConstants.USER_SLAT);
        if (!Objects.equals(userInfo.getPassword(), md5Pwd)) {
            return Result.fail("原密码错误");
        }
        String finalPwd = MD5Util.md5(newPwd, UserConstants.USER_SLAT);
        userInfo.setPassword(finalPwd);
        userInfoMapper.updateById(userInfo);
        return Result.success("修改成功");
    }

    @Override
    public Result<?> logout(HttpServletRequest req, HttpServletResponse res) {
        log.info("用户退出登录,session:{}", req.getSession());
        req.getSession().removeAttribute("user");
        return Result.success("退出成功");
    }

    @Override
    public Result<?> getUserMenus() {
        UserNumVO userNumVO = new UserNumVO();

        QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
        departmentQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long departmentNum = departmentMapper.selectCount(departmentQueryWrapper);
        userNumVO.setDeptNum(departmentNum);

        QueryWrapper<DoctorInfo> doctorInfoQueryWrapper = new QueryWrapper<>();
        doctorInfoQueryWrapper.eq("status", StatusType.ENABLE.getCode());
        Long doctorNum = doctorInfoMapper.selectCount(doctorInfoQueryWrapper);
        userNumVO.setDoctorNum(doctorNum);

        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("status", BedType.FREE.getCode());
        Long roomNum = roomMapper.selectCount(roomQueryWrapper);
        userNumVO.setRoomNum(roomNum);

        QueryWrapper<Bed> bedQueryWrapper = new QueryWrapper<>();
        bedQueryWrapper.eq("status", BedType.FREE.getCode());
        Long bedNum = bedMapper.selectCount(bedQueryWrapper);
        userNumVO.setBedNum(bedNum);

        QueryWrapper<Announcement> announcementQueryWrapper = new QueryWrapper<>();
        announcementQueryWrapper.eq("status", AnnouncementType.PUBLISHED.getCode());
        Long noticeNum = announcementMapper.selectCount(announcementQueryWrapper);
        userNumVO.setNoticeNum(noticeNum);

        return Result.success(userNumVO);
    }
}
