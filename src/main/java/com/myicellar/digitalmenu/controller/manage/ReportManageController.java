package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.ReportService;
import com.myicellar.digitalmenu.vo.request.ReportDayReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ProductReportRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.SupplierReportRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manage/report")
@Api(tags = "报表统计", description = "/manage/report")
public class ReportManageController {
    @Autowired
    private ReportService reportService;

    /**
     * 供应商扫码量报表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/querySupplierReport")
    @ApiOperation("供应商扫码量报表查询-分页")
    public ResultVO<PageResponseVO<SupplierReportRespVO>> querySupplierReport(@RequestBody ReportDayReqVO reqVO) {
        PageResponseVO<SupplierReportRespVO> page = reportService.querySupplierReportPage(reqVO);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            return ResultVO.success(page);
        }
        return ResultVO.success(new PageResponseVO<SupplierReportRespVO>());
    }

    /**
     * 酒品点击量报表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryProductClickReport")
    @ApiOperation("酒品点击量报表查询-分页")
    public ResultVO<PageResponseVO<ProductReportRespVO>> queryProductClickReport(@RequestBody ReportDayReqVO reqVO) {
        PageResponseVO<ProductReportRespVO> page = reportService.queryProductClickReportPage(reqVO);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            return ResultVO.success(page);
        }
        return ResultVO.success(new PageResponseVO<ProductReportRespVO>());
    }

    /**
     * 酒品点单量报表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryProductOrderReport")
    @ApiOperation("酒品点单量报表查询-分页")
    public ResultVO<PageResponseVO<ProductReportRespVO>> queryProductOrderReport(@RequestBody ReportDayReqVO reqVO) {
        PageResponseVO<ProductReportRespVO> page = reportService.queryProductOrderReportPage(reqVO);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            return ResultVO.success(page);
        }
        return ResultVO.success(new PageResponseVO<ProductReportRespVO>());
    }

}
