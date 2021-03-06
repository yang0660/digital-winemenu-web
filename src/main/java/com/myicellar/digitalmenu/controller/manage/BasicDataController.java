package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.*;
import com.myicellar.digitalmenu.enums.AttrCatgEnum;
import com.myicellar.digitalmenu.service.*;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.AttrListReqVO;
import com.myicellar.digitalmenu.vo.request.OriginListReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/basic")
@Api(tags = "基础数据下拉框选项查询", description = "/manage/basic")
public class BasicDataController {

    @Autowired
    private AttrService attrService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private OriginService originService;
    @Autowired
    private WineTypeService wineTypeService;
    @Autowired
    private CriticsService criticsService;
    @Autowired
    private VolumeTypeService volumeTypeService;

    /**
     * 风格/口味/葡萄下拉列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryAttrList")
    @ApiOperation("风格/口味/葡萄下拉列表")
    public ResultVO<List<AttrRespVO>> queryAttrList(@RequestBody AttrListReqVO reqVO) {
        AttrCatgEnum attrCatgEnum = AttrCatgEnum.enumOf(reqVO.getAttrCatgNo());
        if (attrCatgEnum == null) {
            return ResultVO.validError("参数错误！");
        }

        List<Attr> list = attrService.queryListByCatgId(attrCatgEnum.attrCatgId);
        List<AttrRespVO> resultList = new ArrayList<AttrRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, AttrRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 国家下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryCountryList")
    @ApiOperation("国家下拉列表")
    public ResultVO<List<CountryRespVO>> queryCountryList() {
        List<Country> list = countryService.queryList();

        List<CountryRespVO> resultList = new ArrayList<CountryRespVO>();
        if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, CountryRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 产地下拉列表（参数：国家ID）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryRegionListByCountryId")
    @ApiOperation("产地下拉列表（参数：国家ID）")
    public ResultVO<List<OriginRespVO>> queryRegionListByCountryId(@RequestBody OriginListReqVO reqVO) {
        List<Origin> list = originService.queryListByCountryId(reqVO.getCountryId());

        List<OriginRespVO> resultList = new ArrayList<OriginRespVO>();
        if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, OriginRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 酒品类型下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryWineTypeList")
    @ApiOperation("酒品类型下拉列表")
    public ResultVO<List<WineTypeRespVO>> queryWineTypeList() {
        List<WineType> list = wineTypeService.queryList();

        List<WineTypeRespVO> resultList = new ArrayList<WineTypeRespVO>();
        if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, WineTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 评论家/机构下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryCriticsList")
    @ApiOperation("评论家/机构下拉列表")
    public ResultVO<List<CriticsRespVO>> queryCriticsList() {
        List<Critics> list = criticsService.queryList();

        List<CriticsRespVO> resultList = new ArrayList<CriticsRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, CriticsRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 容量下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryVolumeList")
    @ApiOperation("容量下拉列表")
    public ResultVO<List<VolumeTypeRespVO>> queryVolumeList() {
        List<VolumeType> list = volumeTypeService.queryVolumeList();

        List<VolumeTypeRespVO> resultList = new ArrayList<VolumeTypeRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, VolumeTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }

}
