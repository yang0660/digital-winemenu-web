package com.myicellar.digitalmenu.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wxhao
 * @date 2018/3/15
 */
@Data
@Component
@ConfigurationProperties(prefix = "file-upload")
public class FileUploadProperties {

    public static final String CONTEXT_PATH = "/file-upload";

    /**
     * 保存位置
     */
    private String rootLocation;

    /**
     * 文件服务器主机名
     */
    private String serverHost;


}
