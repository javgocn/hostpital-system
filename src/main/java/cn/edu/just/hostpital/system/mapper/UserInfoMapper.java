package cn.edu.just.hostpital.system.mapper;

import cn.edu.just.hostpital.system.model.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 查询某年某月的用户注册数
     * @param year 年份
     * @param month 月份
     * @return 注册数
     */
    @Select("SELECT COUNT(*) FROM user_info WHERE create_time LIKE CONCAT(#{year},'%') AND create_time LIKE CONCAT('_____',#{month},'%')")
    int selectByCreateTime(@Param("year") String year, @Param("month") String month);
}
