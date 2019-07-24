package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.request.ReportDayReqVO;
import com.myicellar.digitalmenu.vo.response.ProductReportRespVO;
import com.myicellar.digitalmenu.vo.response.SupplierReportRespVO;

import java.util.List;

public interface UserBehaviorMapperExt extends UserBehaviorMapper {

    Long selectSupplierReportCount(ReportDayReqVO reqVO);

    List<SupplierReportRespVO> selectSupplierReport(ReportDayReqVO reqVO);

    Long selectProductClickReportCount(ReportDayReqVO reqVO);

    List<ProductReportRespVO> selectProductClickReport(ReportDayReqVO reqVO);

    Long selectProductOrderReportCount(ReportDayReqVO reqVO);

    List<ProductReportRespVO> selectProductOrderReport(ReportDayReqVO reqVO);
}