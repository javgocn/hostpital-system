package cn.edu.just.hostpital.system.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常见 Content-Type
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentType {

    APPLICATION_JSON("application/json", "json"),
    APPLICATION_XML("application/xml", "xml"),
    TEXT_HTML("text/html", "html"),
    TEXT_PLAIN("text/plain", "txt"),
    TEXT_XML("text/xml", "xml"),
    IMAGE_JPEG("image/jpeg", "jpg"),
    IMAGE_PNG("image/png", "png"),
    IMAGE_GIF("image/gif", "gif"),
    IMAGE_BMP("image/bmp", "bmp"),
    IMAGE_TIFF("image/tiff", "tiff"),
    DEFAULT("application/octet-stream", "bin");

    private final String contentType;
    private final String extension;

    public static ContentType of(String contentType) {
        for (ContentType type : ContentType.values()) {
            if (type.getContentType().equals(contentType)) {
                return type;
            }
        }
        return DEFAULT;
    }
}
