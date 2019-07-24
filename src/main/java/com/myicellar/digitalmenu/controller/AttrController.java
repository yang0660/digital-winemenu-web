package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.Attr;
import com.myicellar.digitalmenu.service.AttrService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.AttrRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
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
@RequestMapping("/app/attr")
@Api(tags = "用户页面", description = "/app/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("葡萄列表查询")
    public ResultVO<List<AttrRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {
        List<Attr> list = attrService.queryListBySupplierId(reqVO.getSupplierId());
        List<AttrRespVO> resultList = new ArrayList<AttrRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, AttrRespVO.class);
        }

        return ResultVO.success(resultList);
    }

}
