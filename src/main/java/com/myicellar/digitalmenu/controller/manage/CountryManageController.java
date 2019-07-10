package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Country;
import com.myicellar.digitalmenu.service.CountryService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.response.CountryRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/country")
@Api(tags = "国家", description = "/manage/country")
public class CountryManageController {

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
    public ResultVO<List<CountryRespVO>> queryList() {
        List<Country> page = countryService.queryList();

        PageResponseVO<CountryRespVO> resultPage = new PageResponseVO<CountryRespVO>();
        if(page!=null && page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
    }

}
