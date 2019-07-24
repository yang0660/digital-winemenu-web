package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.UserBehavior;
import com.myicellar.digitalmenu.dao.mapper.UserBehaviorMapperExt;
import com.myicellar.digitalmenu.vo.request.ReportDayReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ProductReportRespVO;
import com.myicellar.digitalmenu.vo.response.SupplierReportRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportService extends BaseService<Long, UserBehavior, UserBehaviorMapperExt> {

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<SupplierReportRespVO> querySupplierReportPage(ReportDayReqVO reqVO) {
        PageResponseVO<SupplierReportRespVO> page = selectPage(reqVO, mapper::selectSupplierReportCount,
                mapper::selectSupplierReport);
        return page;
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<ProductReportRespVO> queryProductClickReportPage(ReportDayReqVO reqVO) {
        PageResponseVO<ProductReportRespVO> page = selectPage(reqVO, mapper::selectProductClickReportCount,
                mapper::selectProductClickReport);
        return page;
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<ProductReportRespVO> queryProductOrderReportPage(ReportDayReqVO reqVO) {
        PageResponseVO<ProductReportRespVO> page = selectPage(reqVO, mapper::selectProductOrderReportCount,
                mapper::selectProductOrderReport);
        return page;
    }
}