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

    @ApiModelProperty(value = "商品ID(扫码行为上传不需要携带此参数)")
    private Long packageId;

    @ApiModelProperty(value = "用户行为类型(1 - 扫描主页二维码; 2 - 添加酒品到wishlist)")
    private Byte type;

}