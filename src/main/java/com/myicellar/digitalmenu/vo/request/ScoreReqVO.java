package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "评分信息")
public class ScoreReqVO {
    @ApiModelProperty(value = "评论机构ID")
    private Long criticsId;

    @ApiModelProperty(value = "评分")
    private BigDecimal score;

    @ApiModelProperty(value = "评分日期")
    private Date tastedAt;
}