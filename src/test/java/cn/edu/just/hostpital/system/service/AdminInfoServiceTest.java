package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.constans.UserConstants;
import cn.edu.just.hostpital.system.enums.StatusType;
import cn.edu.just.hostpital.system.mapper.AdminInfoMapper;
import cn.edu.just.hostpital.system.model.AdminInfo;
import cn.edu.just.hostpital.system.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author javgo.cn
 * @date 2024/1/21
 */
@SpringBootTest
class AdminInfoServiceTest {

    @Resource
    private AdminInfoMapper adminInfoMapper;

    /**
     * 初始化超级管理员账户
     */
    @Test
    void initializeAdmin() {
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setUsername("admin");
        adminInfo.setName("超级管理员");
        adminInfo.setPassword(MD5Util.md5("admin", UserConstants.USER_SLAT));
        adminInfo.setTel("18685848821");
        adminInfo.setEmail("75322363@qq.com");
        adminInfo.setStatus(StatusType.ENABLE.getCode());
        adminInfo.setCreateTime(new Date());
        adminInfo.setUpdateTime(new Date());

        int insert = adminInfoMapper.insert(adminInfo);
        if (insert > 0) {
            System.out.println("初始化超级管理员账户成功");
            System.out.println("账户名：admin");
            System.out.println("密码：admin");
        } else {
            System.out.println("初始化超级管理员账户失败");
            assert false;
        }
    }
}