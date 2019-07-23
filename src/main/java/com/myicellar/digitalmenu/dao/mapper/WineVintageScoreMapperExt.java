package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.vo.response.ScoreRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WineVintageScoreMapperExt extends WineVintageScoreMapper{
    List<ScoreRespVO> selectScoreByProductId(Long productId);

    Integer deleteByWineIdAndVintage(@Param("wineId") Long wineId,
                                     @Param("vintageTag") Long vintageTag);

    List<WineVintageScore> selectScoreListByWineVintage(@Param("wineId") Long wineId,
                                                    @Param("vintageTag") Long vintageTag);

    List<WineVintageScore> selectAwardListByWineVintage(@Param("wineId") Long wineId,
                                                        @Param("vintageTag") Long vintageTag);
}