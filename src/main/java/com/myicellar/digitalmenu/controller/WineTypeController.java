package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.WineType;
import com.myicellar.digitalmenu.service.WineTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineTypeRespVO;
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
@RequestMapping("/app/winetype")
@Api(tags = "用户页面", description = "/app/winetype")
public class WineTypeController {

    @Autowired
    private WineTypeService wineTypeService;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("酒品分类列表查询")
    public ResultVO<List<WineTypeRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {
        List<WineType> list = wineTypeService.queryListBySupplierId(reqVO.getSupplierId());

        List<WineTypeRespVO> resultList = new ArrayList<WineTypeRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,WineTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }

}
