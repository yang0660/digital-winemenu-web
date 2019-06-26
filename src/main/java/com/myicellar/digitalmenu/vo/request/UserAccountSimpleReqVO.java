package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAccountSimpleReqVO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("真实姓名")
    private String realName;
}
