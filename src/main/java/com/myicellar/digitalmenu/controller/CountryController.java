package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.CountryService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
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
@Api(tags = "用户页面", description = "/app/country")
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
    @ApiOperation("国家列表查询")
    public ResultVO<List<CountryRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {
        List<CountryRespVO> list = countryService.queryListBySupplierId(reqVO.getSupplierId());
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<CountryRespVO>();
        }

        return ResultVO.success(list);
    }

}
