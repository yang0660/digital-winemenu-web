package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.UserBehavior;
import com.myicellar.digitalmenu.service.UserBehaviorService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.UserBehaviorReqVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/app/data")
@Api(tags = "用户行为数据上传", description = "/app/data")
public class UserBehaviorManageController {

    @Autowired
    private UserBehaviorService userBehaviorService;

    /**
     * 上传用户行为数据
     *
     * @param
     * @return
     */
    @PostMapping(value = "/uploadUserBehavior")
    @AuthIgnore
    @ApiOperation("上传用户行为数据")
    public ResultVO<Integer> insertUserBehavior(@RequestBody UserBehaviorReqVO reqVO) {
        Integer result = 0;
        try {
            UserBehavior userBehavior = ConvertUtils.convert(reqVO, UserBehavior.class);
            userBehavior.setUploadAt(new Date());

            result = userBehaviorService.insertSelective(userBehavior);
        } catch (Exception e) {
            log.error("上传用户行为数据失败!", e);
        }

        return ResultVO.success(result);
    }

}
