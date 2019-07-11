package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.response.ScoreRespVO;

import java.util.List;

public interface WineVintageScoreMapperExt extends WineVintageScoreMapper{
    List<ScoreRespVO> selectScoreByPackageId(Long packageId);
}