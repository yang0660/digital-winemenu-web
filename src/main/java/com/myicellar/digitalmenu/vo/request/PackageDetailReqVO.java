package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * package详情请求信息
 */
@Data
@ApiModel(value = "package详情请求信息")
public class PackageDetailReqVO {

    @ApiModelProperty(value = "PackageId")
    private Long packageId;
}