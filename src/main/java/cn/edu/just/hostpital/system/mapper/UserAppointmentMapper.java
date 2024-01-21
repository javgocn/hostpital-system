package cn.edu.just.hostpital.system.mapper;

import cn.edu.just.hostpital.system.model.UserAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户预约表 Mapper 接口
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface UserAppointmentMapper extends BaseMapper<UserAppointment> {

    /**
     * 查询某年某月的用户预约数
     * @param year 年份
     * @param month 月份
     * @return 预约数
     */
    @Select("SELECT COUNT(*) FROM user_appointment WHERE create_time LIKE CONCAT(#{year},'%') AND create_time LIKE CONCAT('_____',#{month},'%')")
    int selectByCreateTime(@Param("year") String year, @Param("month") String month);
}
