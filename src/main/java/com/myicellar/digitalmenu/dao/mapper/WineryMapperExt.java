package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WineryMapperExt extends WineryMapper {
    List<Winery> selectListAll();

    long selectCount(WineryPageReqVO reqVO);

    List<Winery> selectList(WineryPageReqVO reqVO);

    Winery selectByName(@Param("wineryNameEng") String wineryNameEng);

    @MapKey("wineryNameEng")
    Map<String,Winery> selectNameMap();
}