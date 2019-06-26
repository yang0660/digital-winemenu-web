package com.myicellar.digitalmenu.vo.request.app;

import com.myicellar.digitalmenu.vo.request.PageRequestVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 修改密码
*/
@Data
@ApiModel(value = "修改密码")
public class UpdatePasswordReqVO extends PageRequestVO {

    @ApiModelProperty("原密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String password;
}