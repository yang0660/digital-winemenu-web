package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;

import java.util.List;

public interface ImgMapperExt extends ImgMapper{
    long selectCount(ImgPageReqVO reqVO);

    List<Img> selectList(ImgPageReqVO reqVO);
}