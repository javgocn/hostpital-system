package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.model.UserRegistration;
import cn.edu.just.hostpital.system.mapper.UserRegistrationMapper;
import cn.edu.just.hostpital.system.service.UserRegistrationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户挂号表 服务实现类
 * </p>
 *
 * @author javgo
 * @since 2024-01-06
 */
@Service
public class UserRegistrationServiceImpl extends ServiceImpl<UserRegistrationMapper, UserRegistration> implements UserRegistrationService {

}
