package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.Origin;
import com.myicellar.digitalmenu.service.OriginService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.OriginListReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.OriginRespVO;
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
@RequestMapping("/app/origin")
@Api(tags = "用户页面-产地", description = "/app/origin")
public class OriginController {

    @Autowired
    private OriginService originService;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<OriginRespVO>> queryListBySupplierId (@RequestBody SupplierIdReqVO reqVO) {
        List<Origin> list = originService.queryListBySupplierId(reqVO.getSupplierId());
        List<OriginRespVO> resultList = new ArrayList<OriginRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,OriginRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 查询产地列表（根据供应商及国家ID）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryListBySupplierIdAndCountryId")
    @AuthIgnore
    @ApiOperation("查询产地列表（根据供应商及国家ID）")
    public ResultVO<List<OriginRespVO>> queryListBySupplierIdAndCountryId (@RequestBody OriginListReqVO reqVO) {
        List<Origin> list = originService.queryListBySupplierIdAndCountryId(reqVO.getSupplierId(),reqVO.getCountryId());
        List<OriginRespVO> resultList = new ArrayList<OriginRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,OriginRespVO.class);
        }

        return ResultVO.success(resultList);
    }

}
