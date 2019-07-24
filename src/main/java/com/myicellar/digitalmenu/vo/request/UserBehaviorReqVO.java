package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户行为数据上传参数
 */
@Data
@ApiModel(value = "用户行为数据上传参数")
public class UserBehaviorReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "productId(扫码上传时不用传)")
    private Long productId;

    @ApiModelProperty(value = "设备类型：android/ios/h5")
    private String deviceType;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "用户行为类型(1 扫描主页二维码，2 查看酒品详情，3 添加酒品到wishlist)")
    private Byte type;

}