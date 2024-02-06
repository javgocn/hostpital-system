package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.dto.UserFeedbackDTO;
import cn.edu.just.hostpital.system.service.UserFeedbackService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户反馈表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
@Api(tags = "用户反馈 API", value = "UserFeedbackController")
@RestController
@RequestMapping("/feedback")
public class UserFeedbackController {

    @Resource
    private UserFeedbackService userFeedbackService;

    @PostMapping("/select/{currentPage}/{size}")
    public Result<?> select(@PathVariable("currentPage") Integer currentPage, @PathVariable("size") Integer size) {
        return userFeedbackService.selectByPage(currentPage, size);
    }

    @PostMapping("/reply")
    public Result<?> reply(@RequestBody UserFeedbackDTO userFeedbackDTO) {
        return userFeedbackService.reply(userFeedbackDTO);
    }

    @PostMapping("/feed")
    public Result<?> feed(@RequestBody UserFeedbackDTO userFeedbackDTO) {
        return userFeedbackService.feed(userFeedbackDTO);
    }

}
