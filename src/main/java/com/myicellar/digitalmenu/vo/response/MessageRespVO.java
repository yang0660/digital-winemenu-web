package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fyf
 * @date created in 14:25 2019/1/4
 * @description :
 **/
@Data
@ApiModel
public class MessageRespVO implements Serializable{
    @ApiModelProperty("id")
    private Long id;
    //公告标题
    @ApiModelProperty("消息标题")
    private String title;
    //公告内容
    @ApiModelProperty("消息内容")
    private String sendContent;

    @ApiModelProperty("发布人姓名")
    private String publishName;
    @ApiModelProperty("接收人姓名")
    private String receiveName;
    @ApiModelProperty("手机号")
    private String mobileNo;
    @ApiModelProperty("接收类型")
    private Byte acceptorType;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("是否已读")
    private Byte isRead;


}
