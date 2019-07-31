package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.vo.response.ScoreRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WineVintageScoreMapperExt extends WineVintageScoreMapper {
    List<ScoreRespVO> selectScoreAwardByProductId(@Param("productId")Long productId,
                                                  @Param("type") Byte type);

    Integer deleteByWineIdAndVintage(@Param("wineId") Long wineId,
                                     @Param("vintageTag") Long vintageTag);

    List<WineVintageScore> selectScoreAwardListByWineVintage(@Param("wineId") Long wineId,
                                                             @Param("vintageTag") Long vintageTag,
                                                             @Param("type") Byte type);

}