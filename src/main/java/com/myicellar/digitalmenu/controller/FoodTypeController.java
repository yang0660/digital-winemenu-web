package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;
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
@RequestMapping("/app/foodtype")
@Api(tags = "用户页面-美食分类", description = "/app/foodtype")
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("美食分类列表查询")
    public ResultVO<List<FoodTypeRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            return ResultVO.validError("supplierId cannot be empty！");
        }

        List<FoodType> list = foodTypeService.queryListBySuppilerId(reqVO.getSupplierId());
        List<FoodTypeRespVO> resultList = new ArrayList<FoodTypeRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,FoodTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }
}
