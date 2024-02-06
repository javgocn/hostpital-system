package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserFeedbackDTO;
import cn.edu.just.hostpital.system.model.UserFeedback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户反馈表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface UserFeedbackService extends IService<UserFeedback> {

    /**
     * 分页查找用户反馈信息
     * @param currentPage 当前页
     * @param size 每页大小
     * @return 用户反馈列表
     */
    Result<?> selectByPage(Integer currentPage, Integer size);

    /**
     * 回复用户反馈
     * @param userFeedbackDTO 用户反馈 DTO
     * @return 回复内容
     */
    Result<?> reply(UserFeedbackDTO userFeedbackDTO);

    /**
     * 发布反馈信息
     * @param userFeedbackDTO 用户反馈 DTO
     * @return 发布的反馈信息
     */
    Result<?> feed(UserFeedbackDTO userFeedbackDTO);
}
