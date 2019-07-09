package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ImgMapperExt extends ImgMapper{
    long selectCount(ImgPageReqVO reqVO);

    List<Img> selectList(ImgPageReqVO reqVO);

    @MapKey("imgId")
    Map<Long,Img> selectImgMapByIds(@Param("imgIds") List<Long> imgIds);
}