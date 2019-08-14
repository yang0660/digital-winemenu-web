package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.dao.mapper.WineVintageScoreMapperExt;
import com.myicellar.digitalmenu.vo.response.ScoreRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class WineVintageScoreService extends BaseService<Long, WineVintageScore, WineVintageScoreMapperExt> {

    /**
     * 查询酒品的评分/获奖信息列表
     *
     * @param productId
     * @return
     */
    public List<ScoreRespVO> queryScoreAwardListByProductId(Long productId,Byte type) {
        return mapper.selectScoreAwardByProductId(productId,type);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer deleteByWineIdAndVintage(Long wineId, Long vintageTag) {
        return mapper.deleteByWineIdAndVintage(wineId, vintageTag);
    }

    public List<WineVintageScore> selectScoreAwardListByWineVintage(Long wineId, Long vintageTag,Byte type) {
        return mapper.selectScoreAwardListByWineVintage(wineId, vintageTag,type);
    }
}