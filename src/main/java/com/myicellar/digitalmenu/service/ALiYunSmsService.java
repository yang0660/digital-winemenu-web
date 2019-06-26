package com.myicellar.digitalmenu.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author zhuhecheng
 * @Date 2019/1/15 0015 下午 12:03
 * @Description 阿里云短信发送工具类,直接调用 SendSmsResponse 方法传入相应参数即可
 * @Version 1.0
 **/
@Service
public class ALiYunSmsService {

    private static Logger logger = LoggerFactory.getLogger(ALiYunSmsService.class);

    @Value(value = "${aliyun-sms.accessKeyId}")
    private String accessKeyId;
    @Value(value = "${aliyun-sms.accessKeySecret}")
    private String accessKeySecret;
    //发送验证码模板的code
    @Value(value = "${aliyun-sms.templateCode}")
    public String templateCode;

    /**
     * 发送验证码短信
     * @param tel 电话,如果需要批量发送，请用","隔开号码即可   如：17770039942,17770039943
     * @param jsonParams json 模板参数  当前发送短信验证码的参数为 {"code":code}
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendMsg(String tel,String jsonParams){
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(tel);
            request.setSignName("安基集团");
            request.setTemplateCode(templateCode);
            request.setTemplateParam(jsonParams);
            request.setOutId("yourOutId");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("发送短信,号码:"+tel+",参数:"+jsonParams+",结果:"+sendSmsResponse.getMessage());
            return sendSmsResponse;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送验证码短信
     * @param tel 电话,如果需要批量发送，请用","隔开号码即可   如：17770039942,17770039943
     * @param jsonParams json 模板参数  当前发送短信验证码的参数为 {"code":code}
     * @param Code 所要发送短信的模板code
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendMsg(String tel,String jsonParams,String Code){
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
            IAcsClient acsClient = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(tel);
            request.setSignName("安基集团");
            request.setTemplateCode(Code);
            request.setTemplateParam(jsonParams);
            request.setOutId("yourOutId");
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("发送短信,号码:"+tel+",参数:"+jsonParams+",结果:"+sendSmsResponse.getMessage());
            return sendSmsResponse;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] strs){
        //sendMsg("13189748386","{'code':'" + StringUtil.createCode() + "'}",templateCode);
    }

}
