package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.dao.entity.WineVintageAttr;
import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.dao.mapper.WineVintageMapperExt;
import com.myicellar.digitalmenu.enums.ScoreAwardTypeEnum;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineVintageService extends BaseService<Long, WineVintage, WineVintageMapperExt> {

    @Autowired
    private WineVintageAttrService wineVintageAttrService;
    @Autowired
    private WineVintageScoreService wineVintageScoreService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 酒品年份列表查询-分页
     *
     * @param reqVO
     * @return
     */
    public PageResponseVO<WineVintageListRespVO> queryPageList(WineVintageListReqVO reqVO) {
        PageResponseVO<WineVintageListRespVO> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        List<String> wineVintageIds = new ArrayList<String>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            List<WineVintageListRespVO> list = page.getItems();
            for (WineVintageListRespVO respVO : list) {
                wineVintageIds.add(respVO.getWineId() + "|" + respVO.getVintageTag());
            }
            //风格
            Map<String, WineAttrMapRespVO> styleAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1001L, wineVintageIds);
            //口味
            Map<String, WineAttrMapRespVO> descriptorAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1002L, wineVintageIds);
            //葡萄
            Map<String, WineAttrMapRespVO> grapAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(101L, wineVintageIds);
            list.forEach(respVO -> {
                //酒精度%
                if (respVO.getAlcoholBps() != null) {
                    respVO.setAlcohol(new BigDecimal(respVO.getAlcoholBps()).divide(new BigDecimal(100)).setScale(2));
                }
                //风格
                if (!CollectionUtils.isEmpty(styleAttrMap)) {
                    WineAttrMapRespVO attrRespVO = styleAttrMap.get(respVO.getWineId() + "|" + respVO.getVintageTag());
                    if (attrRespVO != null && !CollectionUtils.isEmpty(attrRespVO.getList())) {
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setStyle(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
                //口味
                if (!CollectionUtils.isEmpty(descriptorAttrMap)) {
                    WineAttrMapRespVO attrRespVO = descriptorAttrMap.get(respVO.getWineId() + "|" + respVO.getVintageTag());
                    if (attrRespVO != null && !CollectionUtils.isEmpty(attrRespVO.getList())) {
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setDescriptor(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
                //葡萄
                if (!CollectionUtils.isEmpty(grapAttrMap)) {
                    WineAttrMapRespVO attrRespVO = grapAttrMap.get(respVO.getWineId() + "|" + respVO.getVintageTag());
                    if (attrRespVO != null && !CollectionUtils.isEmpty(attrRespVO.getList())) {
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setGrap(wineVintageAttrService.listToEngStr(attrlist));
                    }
                }
            });
        }
        return page;
    }

    /**
     * 查询年份详情
     *
     * @param wineId
     * @param vintageTag
     * @return
     */
    public WineVintageRespVO queryByWineIdAndVintage(Long wineId, Long vintageTag) {
        WineVintage wineVintage = mapper.selectByPrimaryKey(wineId, vintageTag);
        WineVintageRespVO respVO = ConvertUtils.convert(wineVintage, WineVintageRespVO.class);
        if (respVO != null) {
            if (respVO.getAlcoholBps() != null) {
                respVO.setAlcohol(new BigDecimal(respVO.getAlcoholBps()).divide(new BigDecimal(100)).setScale(2));
            }
            List<String> wineVintageIds = new ArrayList<String>();
            String wineVintageStr = respVO.getWineId() + "|" + respVO.getVintageTag();
            wineVintageIds.add(wineVintageStr);
            //风格
            Map<String, WineAttrMapRespVO> styleAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1001L, wineVintageIds);
            //口味
            Map<String, WineAttrMapRespVO> descriptorAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1002L, wineVintageIds);
            //葡萄
            Map<String, WineAttrMapRespVO> grapAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(101L, wineVintageIds);
            if (!CollectionUtils.isEmpty(styleAttrMap)) {
                WineAttrMapRespVO attrRespVO = styleAttrMap.get(wineVintageStr);
                //风格
                if (attrRespVO != null) {
                    List<WineAttrInfoRespVO> attrList = attrRespVO.getList();
                    WineAttrInfoRespVO attrInfoRespVO = attrList.get(0);
                    respVO.setStyleId(attrInfoRespVO.getAttrId());
                }
            }
            if (!CollectionUtils.isEmpty(descriptorAttrMap)) {
                WineAttrMapRespVO attrRespVO = descriptorAttrMap.get(wineVintageStr);
                if (attrRespVO != null) {
                    List<WineAttrInfoRespVO> attrList = attrRespVO.getList();
                    //口味
                    List<Long> descriptorIds = new ArrayList<Long>();
                    for (WineAttrInfoRespVO attrInfoRespVO : attrList) {
                        descriptorIds.add(attrInfoRespVO.getAttrId());
                    }
                    respVO.setDescriptorIds(descriptorIds);
                }
            }
            if (!CollectionUtils.isEmpty(grapAttrMap)) {
                WineAttrMapRespVO attrRespVO = grapAttrMap.get(wineVintageStr);
                if (attrRespVO != null) {
                    List<WineAttrInfoRespVO> attrList = attrRespVO.getList();
                    //葡萄
                    List<GrapRespVO> graps = new ArrayList<GrapRespVO>();
                    for (WineAttrInfoRespVO attrInfoRespVO : attrList) {
                        GrapRespVO grapRespVO = new GrapRespVO();
                        grapRespVO.setGrapId(attrInfoRespVO.getAttrId());
                        if (attrInfoRespVO.getAttrValNum() != null) {
                            grapRespVO.setRate(new BigDecimal(attrInfoRespVO.getAttrValNum())
                                    .divide(new BigDecimal(100).setScale(2)));
                        }
                        graps.add(grapRespVO);
                    }
                    respVO.setGraps(graps);
                }
            }
            //评分、获奖
            List<WineVintageScore> scoreList = wineVintageScoreService.selectScoreAwardListByWineVintage(
                    wineId, vintageTag,ScoreAwardTypeEnum.SCORE.value);
            List<WineVintageScore> awardList = wineVintageScoreService.selectScoreAwardListByWineVintage(
                    wineId, vintageTag,ScoreAwardTypeEnum.AWARD.value);
            if (!CollectionUtils.isEmpty(scoreList)) {
                List<ScoreResponseVO> scores = new ArrayList<ScoreResponseVO>();
                for (WineVintageScore vintageScore : scoreList) {
                    ScoreResponseVO scoreRespVO = new ScoreResponseVO();
                    scoreRespVO.setCriticsId(vintageScore.getWineCriticsId());
                    scoreRespVO.setScore(new BigDecimal(vintageScore.getScoreValNum())
                            .divide(new BigDecimal(100)).setScale(2));
                    scoreRespVO.setTastedAt(vintageScore.getTastedAt());
                    scores.add(scoreRespVO);
                }
                respVO.setScores(scores);
            }
            if (!CollectionUtils.isEmpty(awardList)) {
                List<AwardRespVO> awards = new ArrayList<AwardRespVO>();
                for (WineVintageScore vintageAward : awardList) {
                    AwardRespVO awardRespVO = new AwardRespVO();
                    awardRespVO.setCriticsId(vintageAward.getWineCriticsId());
                    awardRespVO.setScoreName(vintageAward.getScoreName());
                    awardRespVO.setYear(vintageAward.getScoreYear());
                    awards.add(awardRespVO);
                }
                respVO.setAwards(awards);
            }
        }

        return respVO;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer addNew(WineVintageReqVO reqVO) {
        checkNewParam(reqVO);

        WineVintage wineVintage = ConvertUtils.convert(reqVO, WineVintage.class);
        //酒精度BPS
        if (reqVO.getAlcohol() != null) {
            wineVintage.setAlcoholBps(reqVO.getAlcohol().multiply(new BigDecimal(100)).longValue());
        }
        //年份描述
        if (reqVO.getVintageTag() == 1001L) {
            wineVintage.setVintageName("N.V.");
        }
        Date now = new Date();
        wineVintage.setUpdatedAt(now);
        Integer result = mapper.insertSelective(wineVintage);
        if (result > 0) {
            List<Long> attrIds = new ArrayList<Long>();
            if (reqVO.getStyleId() != null && reqVO.getStyleId() != 0L) {
                //风格
                attrIds.add(reqVO.getStyleId());
            }
            if (!CollectionUtils.isEmpty(reqVO.getDescriptorIds())) {
                //口味
                attrIds.addAll(reqVO.getDescriptorIds());
            }

            WineVintageAttr attr = new WineVintageAttr();
            attr.setWineId(reqVO.getWineId());
            attr.setVintageTag(reqVO.getVintageTag());
            attr.setMicRank((short) 9999);
            attr.setUpdatedAt(now);
            if (!CollectionUtils.isEmpty(attrIds)) {
                for (Long attrId : attrIds) {
                    attr.setAttrId(attrId);
                    wineVintageAttrService.insertSelective(attr);
                }
            }
            //葡萄
            if (!CollectionUtils.isEmpty(reqVO.getGraps())) {
                for (GrapReqVO grap : reqVO.getGraps()) {
                    attr.setAttrId(grap.getGrapId());
                    if (grap.getRate() != null) {
                        attr.setAttrValNum(grap.getRate().multiply(new BigDecimal(100)).shortValue());
                    }
                    wineVintageAttrService.insertSelective(attr);
                }
            }
            //评分
            if (!CollectionUtils.isEmpty(reqVO.getScores())) {
                WineVintageScore score = new WineVintageScore();
                score.setWineId(reqVO.getWineId());
                score.setVintageTag(reqVO.getVintageTag());
                score.setUpdatedAt(now);
                for (ScoreReqVO scoreReqVO : reqVO.getScores()) {
                    score.setWineCriticsId(scoreReqVO.getCriticsId());
                    score.setScoreValNum(scoreReqVO.getScore().multiply(new BigDecimal(10)).shortValue());
                    score.setScoreValStr(scoreReqVO.getScore().toString());
                    score.setTastedAt(scoreReqVO.getTastedAt());
                    score.setType(ScoreAwardTypeEnum.SCORE.value);
                    wineVintageScoreService.insertSelective(score);
                }
            }
            //获奖
            if (!CollectionUtils.isEmpty(reqVO.getAwards())) {
                WineVintageScore award = new WineVintageScore();
                award.setWineId(reqVO.getWineId());
                award.setVintageTag(reqVO.getVintageTag());
                award.setUpdatedAt(now);
                for (AwardReqVO awardReqVO : reqVO.getAwards()) {
                    award.setWineCriticsId(awardReqVO.getCriticsId());
                    award.setScoreYear(awardReqVO.getYear());
                    award.setScoreName(awardReqVO.getScoreName());
                    award.setType(ScoreAwardTypeEnum.AWARD.value);
                    wineVintageScoreService.insertSelective(award);
                }
            }
        }

        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer update(WineVintageReqVO reqVO) {
        WineVintage wineVintage = ConvertUtils.convert(reqVO, WineVintage.class);
        //酒精度BPS
        if (reqVO.getAlcohol() != null) {
            wineVintage.setAlcoholBps(reqVO.getAlcohol().multiply(new BigDecimal(100)).longValue());
        }
        //年份描述
        if (reqVO.getVintageTag() == 1001L) {
            wineVintage.setVintageName("N.V.");
        }
        Date now = new Date();
        wineVintage.setUpdatedAt(now);
        Integer result = mapper.updateByPrimaryKeySelective(wineVintage);
        if (result > 0) {
            //删除酒品属性
            Integer deleteNum1 = wineVintageAttrService.deleteByWineIdAndVintage(reqVO.getWineId(),
                    reqVO.getVintageTag());
            List<Long> attrIds = new ArrayList<Long>();
            if (reqVO.getStyleId() != null && reqVO.getStyleId() != 0L) {
                //风格
                attrIds.add(reqVO.getStyleId());
            }
            if (!CollectionUtils.isEmpty(reqVO.getDescriptorIds())) {
                //口味
                attrIds.addAll(reqVO.getDescriptorIds());
            }

            WineVintageAttr attr = new WineVintageAttr();
            attr.setWineId(reqVO.getWineId());
            attr.setVintageTag(reqVO.getVintageTag());
            attr.setMicRank((short) 9999);
            attr.setUpdatedAt(now);
            if (!CollectionUtils.isEmpty(attrIds)) {
                for (Long attrId : attrIds) {
                    attr.setAttrId(attrId);
                    wineVintageAttrService.insertSelective(attr);
                }
            }
            //葡萄
            if (!CollectionUtils.isEmpty(reqVO.getGraps())) {
                for (GrapReqVO grap : reqVO.getGraps()) {
                    attr.setAttrId(grap.getGrapId());
                    if (grap.getRate() != null) {
                        attr.setAttrValNum(grap.getRate().multiply(new BigDecimal(100)).shortValue());
                    }
                    wineVintageAttrService.insertSelective(attr);
                }
            }
            //删除评分、获奖
            Integer deleteNum2 = wineVintageScoreService.deleteByWineIdAndVintage(reqVO.getWineId(),
                    reqVO.getVintageTag());
            //评分
            if (!CollectionUtils.isEmpty(reqVO.getScores())) {
                WineVintageScore score = new WineVintageScore();
                score.setWineId(reqVO.getWineId());
                score.setVintageTag(reqVO.getVintageTag());
                score.setUpdatedAt(now);
                for (ScoreReqVO scoreReqVO : reqVO.getScores()) {
                    score.setWineCriticsId(scoreReqVO.getCriticsId());
                    score.setScoreValNum(scoreReqVO.getScore().multiply(new BigDecimal(10)).shortValue());
                    score.setScoreValStr(scoreReqVO.getScore().toString());
                    score.setTastedAt(scoreReqVO.getTastedAt());
                    score.setType(ScoreAwardTypeEnum.SCORE.value);
                    wineVintageScoreService.insertSelective(score);
                }
            }
            //获奖
            if (!CollectionUtils.isEmpty(reqVO.getAwards())) {
                WineVintageScore award = new WineVintageScore();
                award.setWineId(reqVO.getWineId());
                award.setVintageTag(reqVO.getVintageTag());
                award.setUpdatedAt(now);
                for (AwardReqVO awardReqVO : reqVO.getAwards()) {
                    award.setWineCriticsId(awardReqVO.getCriticsId());
                    award.setScoreYear(awardReqVO.getYear());
                    award.setScoreName(awardReqVO.getScoreName());
                    award.setType(ScoreAwardTypeEnum.AWARD.value);
                    wineVintageScoreService.insertSelective(award);
                }
            }
        }

        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Integer delete(WineVintageInfoReqVO reqVO) {
        //删除酒品属性
        Integer deleteNum1 = wineVintageAttrService.deleteByWineIdAndVintage(reqVO.getWineId(),
                reqVO.getVintageTag());
        //删除评分、获奖
        Integer deleteNum2 = wineVintageScoreService.deleteByWineIdAndVintage(reqVO.getWineId(),
                reqVO.getVintageTag());
        Integer result = mapper.deleteByPrimaryKey(reqVO.getWineId(), reqVO.getVintageTag());

        return result;
    }

    public void checkNewParam(WineVintageReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId can not be empty!");
        }
        if (reqVO.getVintageTag() == null || reqVO.getVintageTag() == 0L) {
            throw new BizException("vintageTage can not be empty!");
        }
        WineVintage wineVintage = mapper.selectByPrimaryKey(reqVO.getWineId(), reqVO.getVintageTag());
        if (wineVintage != null) {
            throw new BizException("vintageTage is already exists!");
        }
    }

    public void checkUpdateParam(WineVintageReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId can not be empty!");
        }
        if (reqVO.getVintageTag() == null || reqVO.getVintageTag() == 0L) {
            throw new BizException("vintageTage can not be empty!");
        }
    }
}