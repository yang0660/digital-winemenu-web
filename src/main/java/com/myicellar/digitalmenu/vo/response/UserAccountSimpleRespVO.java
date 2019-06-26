package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAccountSimpleRespVO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("真实姓名")
    private String realName;
}
