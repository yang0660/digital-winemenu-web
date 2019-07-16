package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.vo.request.WineryNameReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface WineryMapperExt extends WineryMapper {
    long selectCount(WineryPageReqVO reqVO);

    List<Winery> selectList(WineryPageReqVO reqVO);

    long selectByNameCount(WineryNameReqVO reqVO);

    List<Winery> selectByName(WineryNameReqVO reqVO);

    @MapKey("wineryNameEng")
    Map<String,Winery> selectNameMap();
}