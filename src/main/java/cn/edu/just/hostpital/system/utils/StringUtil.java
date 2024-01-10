package cn.edu.just.hostpital.system.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 字符串工具类
 *
 * @author javgo.cn
 * @date 2024/1/10
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    /**
     * 处理对象中所有String类型属性，去除特定特殊字符
     *
     * @param obj 要处理的对象
     */
    public static void processStringProperties(Object obj) {
        if (Objects.isNull(obj)) {
            return;
        }

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                try {
                    String value = (String) field.get(obj);
                    if (Objects.nonNull(value) && StringUtils.isNotBlank(value)) {
                        value = cleanString(value);
                        field.set(obj, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 去除字符串中的特殊字符（非字母数字的字符）和左右空格
     *
     * @param input 输入字符串
     * @return 处理后的字符串
     */
    private static String cleanString(String input) {
        return input.replaceAll("[\\r\\n\\t]", "").trim();
    }
}
