package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 容量
 */
@Data
@ApiModel(value = "容量")
public class VolumeTypeRespVO {
    @ApiModelProperty(value = "容量ID")
    private Long volumeTypeId;

    @ApiModelProperty(value = "容量代码")
    private String volumeTypeCode;

    @ApiModelProperty(value = "容量名称")
    private String typeNameEng;


}