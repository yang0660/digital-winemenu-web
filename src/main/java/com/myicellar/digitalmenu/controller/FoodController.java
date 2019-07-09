package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.FoodDisplayReqVO;
import com.myicellar.digitalmenu.vo.response.FoodDisplayRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/app/food")
@Api(tags = "美食", description = "/app/food")
public class FoodController {

    @Autowired
    private FoodService foodService;


    /**
     * 美食列表页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListByFoodTypeId")
    @AuthIgnore
    @ApiOperation("美食列表页")
    public ResultVO<List<FoodDisplayRespVO>> queryListByFoodTypeId(@RequestBody FoodDisplayReqVO reqVO) {

        List<FoodDisplayRespVO> list = foodService.queryListByFoodTypeId(reqVO);

        List<FoodDisplayRespVO> resultList = new ArrayList<FoodDisplayRespVO>();

        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, FoodDisplayRespVO.class);
        }

        return ResultVO.success(resultList);
    }


}
