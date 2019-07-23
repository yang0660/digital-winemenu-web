package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.dao.mapper.WineVintageScoreMapperExt;
import com.myicellar.digitalmenu.vo.response.ScoreRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WineVintageScoreService extends BaseService<Long, WineVintageScore, WineVintageScoreMapperExt> {

    /**
     * 查询酒品的评分/获奖信息列表
     * @param productId
     * @return
     */
    public List<ScoreRespVO> queryScoreListByProductId(Long productId){
        return mapper.selectScoreByProductId(productId);
    }

    public Integer deleteByWineIdAndVintage(Long wineId, Long vintageTag){
        return mapper.deleteByWineIdAndVintage(wineId, vintageTag);
    }

    public List<WineVintageScore> queryScoreListByWineVintage(Long wineId, Long vintageTag){
        return mapper.selectScoreListByWineVintage(wineId,vintageTag);
    }

    public List<WineVintageScore> queryAwardListByWineVintage(Long wineId, Long vintageTag){
        return mapper.selectAwardListByWineVintage(wineId,vintageTag);
    }
}