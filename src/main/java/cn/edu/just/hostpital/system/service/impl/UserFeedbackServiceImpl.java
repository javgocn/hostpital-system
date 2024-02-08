package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserFeedbackDTO;
import cn.edu.just.hostpital.system.enums.FeedbackType;
import cn.edu.just.hostpital.system.mapper.UserInfoMapper;
import cn.edu.just.hostpital.system.model.UserFeedback;
import cn.edu.just.hostpital.system.mapper.UserFeedbackMapper;
import cn.edu.just.hostpital.system.model.UserInfo;
import cn.edu.just.hostpital.system.service.UserFeedbackService;
import cn.edu.just.hostpital.system.utils.DataTransferUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 用户反馈表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Service
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements UserFeedbackService {

    @Resource
    private UserFeedbackMapper userFeedbackMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<?> selectByPage(Integer currentPage, Integer size) {
        IPage<UserFeedback> page = new Page<>(currentPage, size);
        QueryWrapper<UserFeedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("status", FeedbackType.DELETED.getCode());
        queryWrapper.orderByDesc("create_time");
        IPage<UserFeedback> userFeedbackIPage = userFeedbackMapper.selectPage(page, queryWrapper);
        return Result.success(userFeedbackIPage);
    }

    @Override
    public Result<?> reply(UserFeedbackDTO userFeedbackDTO) {
        UserFeedback userFeedback = DataTransferUtil.shallowCopy(userFeedbackDTO, UserFeedback.class);
        if (Objects.equals(userFeedback.getStatus(), FeedbackType.REPLIED.getCode())) {
            return Result.fail("该主题已回复");
        }
        userFeedback.setStatus(FeedbackType.REPLIED.getCode());
        userFeedbackMapper.updateById(userFeedback);
        return Result.success("回复成功");
    }

    @Override
    public Result<?> feed(UserFeedbackDTO userFeedbackDTO) {
        UserFeedback userFeedback = DataTransferUtil.shallowCopy(userFeedbackDTO, UserFeedback.class);
        UserInfo userInfo = userInfoMapper.selectById(userFeedback.getUserId());
        userFeedback.setUserName(userInfo.getName());
        userFeedback.setCreateTime(new Date());
        int count = userFeedbackMapper.insert(userFeedback);
        return count == 1 ? Result.success("发布成功") : Result.fail("发布失败");
    }
}
