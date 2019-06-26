package com.myicellar.digitalmenu.vo.request.app;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* api消息推送参数
*/
@ApiModel("api消息推送参数")
@Data
public class ApiSendPushMsgReqVO {

    @ApiModelProperty(value = "接收人：（经销商/供应商）单位编号或员工工号", required = true)
    private String acceptor;

    @ApiModelProperty(value = "消息标题")
    private String title;

    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;

    @ApiModelProperty(value = "身份令牌")
    private String key;
}