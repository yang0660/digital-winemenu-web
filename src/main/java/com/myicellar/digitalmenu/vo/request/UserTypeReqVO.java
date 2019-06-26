package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserTypeReqVO implements Serializable{
    private static final long serialVersionUID = -158113027996370810L;
    @ApiModelProperty("用户类型 2、供应商用户 3、经销商用户")
    @NotNull
    private Byte userType;
}
