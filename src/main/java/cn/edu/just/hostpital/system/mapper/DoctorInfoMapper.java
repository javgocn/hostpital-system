package cn.edu.just.hostpital.system.mapper;

import cn.edu.just.hostpital.system.model.DoctorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 医生信息表 Mapper 接口
 * </p>
 *
 * @author javgo
 * @since 2024-01-07
 */
public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {

    @Select("SELECT COALESCE(MAX(CAST(SUBSTRING(job_number FROM LOCATE('-', job_number, LOCATE('-', job_number) + 1) + 1) AS UNSIGNED)), 0) AS max_number FROM doctor_info")
    int getMaxJobNumber();
}
