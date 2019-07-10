package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.Country;
import com.myicellar.digitalmenu.service.CountryService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.CountryRespVO;
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
@RequestMapping("/app/country")
@Api(tags = "用户页面-国家", description = "/app/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<CountryRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {
        List<Country> list = countryService.queryListBySupplierId(reqVO.getSupplierId());

        List<CountryRespVO> resultList = new ArrayList<CountryRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,CountryRespVO.class);
        }

        return ResultVO.success(resultList);
    }

}