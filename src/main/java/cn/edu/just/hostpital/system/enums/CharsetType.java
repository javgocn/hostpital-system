package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常见编码枚举
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CharsetType {

    UTF_8("UTF-8", "八位 UCS 转换格式"),
    ISO_8859_1("ISO-8859-1", "拉丁字母表No.1"),
    GBK("GBK", "国标2312的扩展"),
    GB2312("GB2312", "简体中文汉字编码标准"),
    ASCII("ASCII", "美国标准信息交换码");

    private final String charset;
    private final String description;

    public static CharsetType of(String charset) {
        for (CharsetType charsetType : CharsetType.values()) {
            if (charsetType.getCharset().equals(charset)) {
                return charsetType;
            }
        }
        return null;
    }
}
