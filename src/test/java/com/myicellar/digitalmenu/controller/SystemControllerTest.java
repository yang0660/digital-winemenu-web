package com.myicellar.digitalmenu.controller;

import com.alibaba.fastjson.JSONObject;
import com.myicellar.digitalmenu.DigitalWineMenuApplication;
import com.myicellar.digitalmenu.dao.mapper.UserAccountMapperExt;
import com.myicellar.digitalmenu.vo.request.UserAccountReqVO;
import com.myicellar.digitalmenu.vo.request.UserPageReqVO;
import com.myicellar.digitalmenu.vo.response.LoginRespVO;
import com.myicellar.digitalmenu.vo.response.UserAccountRespVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DigitalWineMenuApplication.class)
@Slf4j
public class SystemControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;



    protected Subject subject;

    private MockHttpSession session;

    @Resource
    org.apache.shiro.web.mgt.WebSecurityManager securityManager;

    @Autowired
    private UserAccountMapperExt userAccountMapperExt;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        SecurityUtils.setSecurityManager(securityManager);
        ThreadContext.bind(securityManager);
        this.session = new MockHttpSession();
    }

    @Test
    public void isLogin() throws Exception {
        RequestBuilder requestBuilder = null;
        requestBuilder = get("/isLogin").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .session((MockHttpSession) getLoginSession());
        String responseString = mockMvc.perform(requestBuilder).andExpect(status().isOk()) // 返回的状态是200
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("json = " + responseString);
    }

    @Test
    @Rollback(false)
    public void login() throws Exception {
        /*UserAccountRespVO user = new UserAccountRespVO();
        user.setMobile("123456");
        user.setPassword("123");
        UserAccount userAccount = userAccountMapperExt.selectByMobile("123456");
        if(userAccount == null) {
            UserAccount account = new UserAccount();
            account.setMobile("123456");
            account.setPassword("37db6629bc073bcd74e0ed84dee75b22");
            account.setCreateTime(new Date());
            account.setCreateUser(1L);
            account.setMaterialStatus((byte)1);
            account.setSalt("456");
            account.setUpdateTime(new Date());
            account.setUserType((byte)1);
            account.setUserId(14654654L);
            account.setUpdateUser(1L);
            userAccountMapperExt.insertSelective(account);
        }
*/

        UserAccountReqVO user = new UserAccountReqVO();

        user.setUserName("lile");
        //user.setMobile("123456");
        user.setPassword("123456");
        System.out.println("JSONObject.toJSONString(user):"+JSONObject.toJSONString(user));

        MvcResult result = this.mockMvc.perform(get("/manage/login") // 请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // 数据的格式
                .content(JSONObject.toJSONString(user))
                .accept(MediaType.APPLICATION_JSON_UTF8)// 添加参数
        ).andExpect(status().isOk()) // 返回的状态是200
                .andReturn();
        LoginRespVO loginUser = (LoginRespVO)result.getRequest().getSession().getAttribute("loginUser");
        System.out.println("loginUser:" + JSONObject.toJSONString(loginUser));
        String responseString = result.getResponse().getContentAsString(); // 将相应的数据转换为字符串
//        assertTrue(responseString.contains("\"resultInfo\":\"1\""));
        System.out.println("json = " + responseString);
    }



    @Test
    public void logout() throws Exception {

        UserAccountRespVO user = new UserAccountRespVO();
        user.setMobile("123456");
        user.setPassword("123");

        MvcResult result = this.mockMvc.perform(get("/login") // 请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // 数据的格式
                .content(JSONObject.toJSONString(user))
                .accept(MediaType.APPLICATION_JSON_UTF8)// 添加参数
        ).andExpect(status().isOk()) // 返回的状态是200
                .andReturn();
        String userid = result.getRequest().getSession().getAttribute("userid").toString();
        System.out.println("userid:" + userid);

        String responseString = result.getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("json = " + responseString);
        result = mockMvc.perform(get("/logout") // 请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // 数据的格式
                .content("")
                .session((MockHttpSession) result.getRequest().getSession())
                .accept(MediaType.APPLICATION_JSON_UTF8)// 添加参数
        ).andExpect(status().isOk()) // 返回的状态是200
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn();
        responseString = result.getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("json = " + responseString);


        responseString = mockMvc.perform(get("/isLogin") // 请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .session((MockHttpSession) result.getRequest().getSession())// 数据的格式
        ).andExpect(status().isOk()) // 返回的状态是200
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("json = " + responseString);
        assertTrue(responseString.contains("\"loginRes\":false"));

    }


    /**
     * 获取登入信息session
     *
     * @return
     * @throws Exception
     */
    private HttpSession getLoginSession() throws Exception {
        UserAccountRespVO user = new UserAccountRespVO();
        user.setMobile("lile");
        user.setPassword("123456");
        MvcResult result = this.mockMvc.perform(get("/manage/login") // 请求的url,请求的方法是get
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // 数据的格式
                .content(JSONObject.toJSONString(user)) // 添加参数
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk()) // 返回的状态是200
                .andReturn();
        return result.getRequest().getSession();
    }

    @Test
    public void testMd5() {
        Md5Hash hash = new Md5Hash("123", "456", 3);
        System.out.println("hash:" + hash.toString());

    }

    @Test
    public void queryListByCondition() throws Exception {
        UserPageReqVO reqVO = new UserPageReqVO();
        reqVO.setCreateTime(new Date());
        RequestBuilder requestBuilder = null;
        System.out.println("JSONObject.toJSONString(reqVO):"+JSONObject.toJSONString(reqVO));
        requestBuilder = post("/manage/user/queryListByCondition")
                .content(JSONObject.toJSONString(reqVO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .session((MockHttpSession) getLoginSession())
        ;
        String responseString = mockMvc.perform(requestBuilder).andExpect(status().isOk()) // 返回的状态是200
                .andDo(print()) // 打印出请求和相应的内容
                .andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
        System.out.println("json = " + responseString);

    }



}
