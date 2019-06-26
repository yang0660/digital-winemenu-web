package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 推送基础数据
*/
@Data
public class UploadPushInfoReqVO {

    @ApiModelProperty("设备ID")
    private String deviceId;

    @ApiModelProperty("设备类型：Android/IOS")
    private String deviceType;

    private Long userId;

}