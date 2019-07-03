package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;

import java.util.List;

public interface ImgTypeMapperExt extends ImgTypeMapper{
    long selectCount(ImgTypePageReqVO reqVO);

    List<ImgType> selectList(ImgTypePageReqVO reqVO);
}