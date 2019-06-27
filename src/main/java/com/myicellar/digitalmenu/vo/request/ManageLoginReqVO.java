package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* 用户帐号表
*/
@Data
@ApiModel(value = "用户帐户说明")
public class ManageLoginReqVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;
    
    @ApiModelProperty("密码")
    private String password;
}