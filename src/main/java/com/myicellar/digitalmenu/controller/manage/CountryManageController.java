package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Country;
import com.myicellar.digitalmenu.service.CountryService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.CountryDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.CountryReqVO;
import com.myicellar.digitalmenu.vo.request.CountryUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.CountryRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
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
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/country")
@Api(tags = "国家", description = "/manage/country")
public class CountryManageController {

    @Autowired
    private CountryService countryService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

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




    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<CountryRespVO> add(@RequestBody CountryReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Country country = ConvertUtils.convert(reqVO,Country.class);
        country.setCountryId(snowflakeIdWorker.nextId());

        Date now = new Date();

        country.setUpdatedAt(now);
        int i = countryService.insertSelective(country);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        CountryRespVO respVO = ConvertUtils.convert(country,CountryRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody CountryUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Country country = ConvertUtils.convert(reqVO,Country.class);
        Date now = new Date();
        country.setUpdatedAt(now);
        int i = countryService.updateByPrimaryKeySelective(country);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        CountryRespVO respVO = ConvertUtils.convert(country,CountryRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody CountryDeleteReqVO reqVO) {
        if(reqVO.getCountryId()==null || reqVO.getCountryId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = countryService.deleteByPrimaryKey(reqVO.getCountryId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(CountryReqVO reqVO){
        if(reqVO.getCountryId()==null || reqVO.getCountryId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getCountryNameEng())){
            throw new BizException("countryNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(CountryUpdateReqVO reqVO){
        if(reqVO.getCountryId()==null || reqVO.getCountryId()==0L){
            throw new BizException("countryId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getCountryNameEng())){
            throw new BizException("countryNameEng cannot be empty!");
        }
    }

}
