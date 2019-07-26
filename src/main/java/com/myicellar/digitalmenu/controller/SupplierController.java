package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.SupplierService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.SupplierRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/app/supplier")
@Api(tags = "用户页面", description = "/app/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 查询供应商信息
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryBySupplierId")
    @AuthIgnore
    @ApiOperation("查询供应商信息")
    public ResultVO<SupplierRespVO> queryByWineryId(@RequestBody SupplierIdReqVO reqVO) {
        SupplierRespVO respVO = supplierService.queryBySupplierId(reqVO.getSupplierId());
        if (respVO == null) {
            respVO = new SupplierRespVO();
        }
        return ResultVO.success(respVO);
    }
}
