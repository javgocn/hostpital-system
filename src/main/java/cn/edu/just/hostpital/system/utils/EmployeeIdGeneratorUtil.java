package cn.edu.just.hostpital.system.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 员工工号生成工具类
 *
 * @author javgo.cn
 * @date 2024/1/23
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmployeeIdGeneratorUtil {

    public static final String DOCTOR_PREFIX = "D";
    public static final String NURSE_PREFIX = "N";
    public static final String SEPARATOR = "-";

    public static String generateEmployeeId(String position, String departmentName, Integer maxId) {
        String pinyin = convertToPinyin(StringUtils.trim(departmentName));
        return position + SEPARATOR + pinyin + SEPARATOR + maxId;
    }

    private static String convertToPinyin(String departmentName) {
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] chars = departmentName.toCharArray();
        for (char c : chars) {
            try {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (Objects.isNull(pinyinArray) && pinyinArray.length > 0) {
                    pinyin.append(pinyinArray[0]);
                } else {
                    pinyin.append(c);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }
        return pinyin.toString();
    }
}
