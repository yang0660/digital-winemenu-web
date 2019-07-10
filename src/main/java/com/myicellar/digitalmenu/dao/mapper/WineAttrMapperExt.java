package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineAttr;
import com.myicellar.digitalmenu.vo.request.WineAttrPageReqVO;
import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WineAttrMapperExt extends WineAttrMapper{
    long selectCount(WineAttrPageReqVO reqVO);

    List<WineAttr> selectList(WineAttrPageReqVO reqVO);

    @MapKey("wineId")
    Map<Long,WineAttrMapRespVO> selectAttrMapByWineIds(@Param("attrCatgId") Long attrCatgId,
                                                   @Param("wineIds") List<Long> wineIds);
}