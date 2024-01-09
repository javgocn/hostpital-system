package cn.edu.just.hostpital.system.utils;

import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5 工具类
 *
 * @author javgo.cn
 * @date 2024/1/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MD5Util {

    /**
     * MD5 加密用来进行位运算的盐值
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(String... text) {
        if (text == null || text.length == 0 || Arrays.stream(text).allMatch(StrUtil::isBlank)) {
            throw new IllegalArgumentException("提供的待加密字符串不能为空或 null");
        }
        // 将所有字符串拼接起来
        String combinedText = String.join("", text);
        // 进行加密（默认使用 UTF-8 编码）
        return md5(combinedText, StandardCharsets.UTF_8);
    }

    public static String md5(String text, Charset charset) {
        // 声明一个加密算法对象
        MessageDigest msgDigest;
        try {
            // 创建一个加密算法为 MD5 的 MessageDigest 对象
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("系统不支持 MD5 加密算法", e);
        }
        // 更新要加密的内容
        msgDigest.update(text.getBytes(charset));
        // 完成加密，返回加密后的结果
        byte[] digested = msgDigest.digest();
        return new String(encodeHex(digested));
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param data 字节数组
     * @return 十六进制字符串
     */
    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }
}
