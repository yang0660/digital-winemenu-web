package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {
    @ApiModelProperty("原始文件名")
    private String originalFilename;

    @ApiModelProperty("文件URL - 服务端返回值， 前端不用传")
    private String url;

    @ApiModelProperty("base64文件")
    private String base64Data;

    public FileVO(String originalFilename, String url) {
        this.originalFilename = originalFilename;
        this.url = url;
    }

}
