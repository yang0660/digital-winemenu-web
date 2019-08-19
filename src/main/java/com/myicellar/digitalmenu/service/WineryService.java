package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.dao.mapper.WineryMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.WineryDetailReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.request.WineryReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineryRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineryService extends BaseService<Long, Winery, WineryMapperExt> {

    @Autowired
    private ImgService imgService;

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(WineryReqVO reqVO) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getWineryNameEng())) {
            throw new BizException("wineryNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0) {
            throw new BizException("winery Logo cannot be empty!");
        }
        if (reqVO.getBannerImgId() == null || reqVO.getBannerImgId() == 0) {
            throw new BizException("winery Banner cannot be empty!");
        }
        if (reqVO.getWineryImgIds().isEmpty()) {
            throw new BizException("winery Image cannot be empty!");
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getAboutUrl())) {
            throw new BizException("Url cannot be empty!");
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("Note cannot be empty!");
        }

        //判断酒庄名字是否已经存在
        Winery winery = queryByName(reqVO.getWineryNameEng());
        if (winery != null) {
            throw new BizException("winery already exists");
        }

    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(WineryReqVO reqVO) {
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId cannot be empty!");
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getWineryNameEng())) {
            throw new BizException("wineryNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0) {
            throw new BizException("winery Logo cannot be empty!");
        }
        if (reqVO.getBannerImgId() == null || reqVO.getBannerImgId() == 0) {
            throw new BizException("winery Banner cannot be empty!");
        }
        if (reqVO.getWineryImgIds().isEmpty()) {
            throw new BizException("winery Image cannot be empty!");
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getAboutUrl())) {
            throw new BizException("Url cannot be empty!");
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("Note cannot be empty!");
        }

        Winery winery = queryByName(reqVO.getWineryNameEng());
        if (winery != null && !winery.getWineryId().equals(reqVO.getWineryId())) {
            throw new BizException("winery already exists");
        }
    }

    @Transactional
    public synchronized ResultVO<Integer> addNew(WineryReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);

        Winery winery = ConvertUtils.convert(reqVO, Winery.class);
        winery.setWineryId(snowflakeIdWorker.nextId());
        winery.setCreatedBy(0L);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setCreatedAt(now);
        winery.setUpdatedAt(now);
        List<Long> longIds = reqVO.getWineryImgIds();
        String stringIds = org.apache.commons.lang3.StringUtils.join(longIds, ",");
        winery.setWineryImgIds(stringIds);
        Integer result = mapper.insertSelective(winery);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO<Integer> update(WineryReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Winery winery = ConvertUtils.convert(reqVO, Winery.class);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setUpdatedAt(now);
        List<Long> longIds = reqVO.getWineryImgIds();
        String stringIds = org.apache.commons.lang3.StringUtils.join(longIds, ",");
        winery.setWineryImgIds(stringIds);
        Integer result= mapper.updateByPrimaryKeySelective(winery);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);
    }

    public List<Winery> queryListAll() {
        return mapper.selectListAll();
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<WineryRespVO> queryPageList(WineryPageReqVO reqVO) {
        PageResponseVO<Winery> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        PageResponseVO<WineryRespVO> resultPage = new PageResponseVO<WineryRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, WineryRespVO.class);
            List<WineryRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (WineryRespVO respVO : list) {
                if (respVO.getLogoImgId() != null && respVO.getLogoImgId() != 0L) {
                    imgIds.add(respVO.getLogoImgId());
                }
            }
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(info -> {
                    if (info.getLogoImgId() != null && info.getLogoImgId() != 0L) {
                        Img logoImg = imgMap.get(info.getLogoImgId());
                        if (logoImg != null) {
                            info.setWineryLogoUrl(logoImg.getImgUrl());
                        }
                    }
                });
            }


            resultPage.setItems(list);
        }

        return resultPage;
    }

    /**
     * 酒庄查询-按酒庄ID
     *
     * @return
     */
    public WineryRespVO queryByWineryId(WineryDetailReqVO reqVO) {
        Winery winery = mapper.selectByPrimaryKey(reqVO.getWineryId());
        WineryRespVO respVO = new WineryRespVO();
        if (winery != null) {
            respVO = ConvertUtils.convert(winery, WineryRespVO.class);
            fillWineryImage(winery, respVO);
        }

        return respVO;

    }

    public void fillWineryImage(Winery winery, WineryRespVO respVO) {
        List<Long> imgIds = new ArrayList<Long>();
        if (winery.getLogoImgId() != null && winery.getLogoImgId() != 0L) {
            imgIds.add(winery.getLogoImgId());
        }
        if (winery.getBannerImgId() != null && winery.getBannerImgId() != 0L) {
            imgIds.add(winery.getBannerImgId());
        }

        List<Long> wineryImgIds = new ArrayList<Long>();
        if (StringUtils.isNotEmpty(winery.getWineryImgIds())) {
            try {
                String[] imgIdArr = winery.getWineryImgIds().split(",");
                for (String imgIdStr : imgIdArr) {
                    Long wineryImgId = Long.valueOf(imgIdStr);
                    wineryImgIds.add(wineryImgId);
                    imgIds.add(wineryImgId);
                }
                respVO.setWineryImgIds(wineryImgIds);
            } catch (Exception e) {
                log.error("解析酒庄图片失败！", e);
            }
        }

        Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
        if (!CollectionUtils.isEmpty(imgMap)) {
            if (winery.getLogoImgId() != null && winery.getLogoImgId() != 0L) {
                Img logoImg = imgMap.get(winery.getLogoImgId());
                if (logoImg != null) {
                    respVO.setWineryLogoUrl(logoImg.getImgUrl());
                }
            }
            if (winery.getBannerImgId() != null && winery.getBannerImgId() != 0L) {
                Img bannerImg = imgMap.get(respVO.getBannerImgId());
                if (bannerImg != null) {
                    respVO.setBannerImgUrl(bannerImg.getImgUrl());
                }
            }
            if (!CollectionUtils.isEmpty(wineryImgIds)) {
                List<String> wineryImgUrls = new ArrayList<String>();
                for (Long wineryImgId : wineryImgIds) {
                    Img wineryImg = imgMap.get(wineryImgId);
                    if (wineryImg != null) {
                        wineryImgUrls.add(wineryImg.getImgUrl());
                    }
                }
                respVO.setWineryImgUrls(wineryImgUrls);
            }
        }
    }

    /**
     * 酒庄查询-按酒庄名称
     *
     * @return
     */
    public Winery queryByName(String wineryNameEng) {
        return mapper.selectByName(wineryNameEng);
    }

    public Map<String, Winery> queryNameMap() {
        return mapper.selectNameMap();
    }
}