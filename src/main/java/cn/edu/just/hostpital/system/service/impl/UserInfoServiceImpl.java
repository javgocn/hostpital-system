package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.model.UserInfo;
import cn.edu.just.hostpital.system.mapper.UserInfoMapper;
import cn.edu.just.hostpital.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
