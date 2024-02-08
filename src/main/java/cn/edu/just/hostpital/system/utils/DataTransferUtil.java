package cn.edu.just.hostpital.system.utils;

import cn.edu.just.hostpital.system.dto.PrescriptionInfoDTO;
import cn.edu.just.hostpital.system.model.PositionInfo;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * 数据转换工具类
 *
 * @author javgo.cn
 * @date 2024/1/9
 */
public final class DataTransferUtil {

    /**
     * 使用 DozerBeanMapper 单例模式
     */
    // private static final Mapper dozerMapper = DozerBeanMapperBuilder.create().withMappingFiles("classpath:dozer/dozer-mapping.xml").build();

    /**
     * 使用 Spring 的 BeanUtils 进行浅拷贝，适用于简单对象和基本数据类型的转换。
     *
     * @param source      源对象
     * @param targetClass 目标类的Class对象
     * @return 目标类的实例
     */
    public static <S, T> T shallowCopy(S source, Class<T> targetClass) {
        if (Objects.isNull(source)) {
            return null;
        }
        T target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 使用 Dozer 进行深拷贝，适用于复杂对象和需要完整复制的场景。
     *
     * @param source      源对象
     * @param targetClass 目标类的 Class 对象
     * @return 目标类的实例
     */
    public static <S, T> T deepCopy(S source, Class<T> targetClass) {
        // return dozerMapper.map(source, targetClass);
        return null;
    }

    /**
     * 使用 Dozer 进行自定义映射，可以处理自定义转换器和复杂映射规则。
     *
     * @param source      源对象
     * @param targetClass 目标类的 Class 对象
     * @param mapId       映射标识符，对应 dozer 映射文件中的 map-id
     * @return 目标类的实例
     */
    public static <S, T> T customMap(S source, Class<T> targetClass, String mapId) {
        // return dozerMapper.map(source, targetClass, mapId);
        return null;
    }
}
