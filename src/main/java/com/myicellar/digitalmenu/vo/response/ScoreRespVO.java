package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 获奖返回信息
 */
@Data
@ApiModel(value = "获奖返回信息")
public class ScoreRespVO {

    @ApiModelProperty(value = "packageId")
    private Long packageId;

}