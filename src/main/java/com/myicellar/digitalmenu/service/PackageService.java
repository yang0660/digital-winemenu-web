package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.mapper.*;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PackageService extends BaseService<Long, IPackage, IPackageMapperExt> {

    @Autowired
    private ImgMapperExt imgMapperExt;

    @Autowired
    private WineAttrMapperExt wineAttrMapperExt;

    @Autowired
    private WineVintageScoreMapperExt wineVintageScoreMapperExt;

    @Autowired
    private FoodMapperExt foodMapperExt;


    public PackagePriceRangeRespVO queryPriceRange(Long supplierId){
        return mapper.selectPriceRange(supplierId);
    }

    /**
     * 查询供应商的推荐酒品列表
     * @param supplierId
     * @return
     */
    public List<PackageInfoRespVO> queryRecomendPackageList(Long supplierId){
        List<PackageInfoRespVO>  list = mapper.selectRecomendPackageList(supplierId);
        fillPackageInfoRespVO(list);

        return list;
    }

    /**
     * 查询美食的酒品列表
     * @param foodId
     * @return
     */
    public List<PackageInfoRespVO> queryPackageListByFoodId(Long foodId){
        List<PackageInfoRespVO>  list = mapper.selectPackageListByFoodId(foodId);
        fillPackageInfoRespVO(list);

        return list;
    }

    /**
     * 查询wishlist酒品列表
     * @param packageIds
     * @return
     */
    public List<PackageInfoRespVO> queryPackageListByIds(List<Long> packageIds){
        List<PackageInfoRespVO>  list = mapper.selectPackageListByIds(packageIds);
        fillPackageInfoRespVO(list);

        return list;
    }

    public void fillPackageInfoRespVO(List<PackageInfoRespVO>  list){
        if(!CollectionUtils.isEmpty(list)){
            List<Long> imgIds = new ArrayList<Long>();
            List<Long> wineIds = new ArrayList<Long>();
            for(PackageInfoRespVO respVO : list){
                wineIds.add(respVO.getWineId());
                if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L){
                    imgIds.add(respVO.getWineImgId());
                }
                if(respVO.getWineryLogoId()!=null && respVO.getWineryLogoId()!=0L){
                    imgIds.add(respVO.getWineryLogoId());
                }
            }

            Map<Long,WineAttrMapRespVO> wineAttrMap = wineAttrMapperExt.selectAttrMapByWineIds(101L,wineIds);
            if(!CollectionUtils.isEmpty(wineAttrMap)){
                list.forEach(info -> {
                    WineAttrMapRespVO respVO = wineAttrMap.get(info.getWineId());
                    if(respVO!=null && !CollectionUtils.isEmpty(respVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = respVO.getList();
                        info.setVariety(listToEngStr(attrlist));
                    }
                });
            }

            if(!CollectionUtils.isEmpty(imgIds)){
                Map<Long, Img>  imgMap = imgMapperExt.selectImgMapByIds(imgIds);
                list.forEach(info -> {
                    if(info.getWineImgId()!=null && info.getWineImgId()!=0L){
                        Img img = imgMap.get(info.getWineImgId());
                        if(img!=null) {
                            info.setWineImgUrl(img.getImgUrl());
                            info.setWineSmallImgUrl(img.getSmallImgUrl());
                        }
                    }
                    if(info.getWineryLogoId()!=null && info.getWineryLogoId()!=0L){
                        Img logoImg = imgMap.get(info.getWineryLogoId());
                        if(logoImg!=null) {
                            info.setWineryLogoUrl(logoImg.getImgUrl());
                            info.setWineryLogoSmallUrl(logoImg.getSmallImgUrl());
                        }
                    }
                });
            }
        }
    }

    public String listToEngStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameEng())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameEng());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    public String listToChsStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameChs())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameChs());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    public String listToChtStr(List<WineAttrInfoRespVO> list){
        StringBuffer subStr = new StringBuffer("");
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(WineAttrInfoRespVO info : list){
                if(!StringUtils.isEmpty(info.getAttrNameCht())) {
                    if (i != 0) {
                        subStr.append(", ");
                    }
                    subStr.append(info.getAttrNameCht());
                    i++;
                }
            }
        }
        return subStr.toString();
    }

    /**
     * 筛选结果总量统计
     * @return
     */
    public Long queryResultCount(PackageFilterReqVO reqVO){
        return mapper.selectResultCount(reqVO);
    }

    /**
     * 筛选结果-分页
     * @return
     */
    public PageResponseVO<PackageInfoRespVO> queryResultPage(PackageFilterReqVO reqVO){
        PageResponseVO<PackageInfoRespVO> page = selectPage(reqVO, mapper::selectResultCount, mapper::selectResult);

        List<PackageInfoRespVO> list = page.getItems();
        fillPackageInfoRespVO(list);
        page.setItems(list);

        return page;
    }

    /**
     * package详情
     * @return
     */
    public PackageDetailRespVO queryDetailById(Long packageId) {
        PackageDetailRespVO respVO = mapper.selectDetailById(packageId);
        fillPackageDetailRespVO(respVO);

        return respVO;
    }

    public void fillPackageDetailRespVO(PackageDetailRespVO  respVO){
        if(respVO!=null){
            //酒精度
            if(respVO.getAlcoholBps()!=null && respVO.getAlcoholBps()!=0L){
                BigDecimal alcohol = new BigDecimal(respVO.getAlcoholBps()).divide(new BigDecimal(100)).setScale(2);
                respVO.setAlcohol(alcohol);
            }

            List<Long> imgIds = new ArrayList<Long>();
            List<Long> wineIds = new ArrayList<Long>();
            wineIds.add(respVO.getWineId());
            if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L){
                imgIds.add(respVO.getWineImgId());
            }
            if(respVO.getWineryLogoId()!=null && respVO.getWineryLogoId()!=0L){
                imgIds.add(respVO.getWineryLogoId());
            }
            if(respVO.getBannerImgId()!=null && respVO.getBannerImgId()!=0L){
                imgIds.add(respVO.getBannerImgId());
            }

            List<Long> wineryImgIds = new ArrayList<Long>();
            if(StringUtils.isNotEmpty(respVO.getWineryImgIds())){
                try {
                    String[] imgIdArr = respVO.getWineryImgIds().split(",");
                    for (String imgIdStr : imgIdArr) {
                        Long wineryImgId = Long.valueOf(imgIdStr);
                        wineryImgIds.add(wineryImgId);
                        imgIds.add(wineryImgId);
                    }
                }catch (Exception e){
                    log.error("解析酒庄图片失败！",e);
                }
            }

            Map<Long,WineAttrMapRespVO> wineAttrMap = wineAttrMapperExt.selectAttrMapByWineIds(101L,wineIds);
            if(!CollectionUtils.isEmpty(wineAttrMap)){
                    WineAttrMapRespVO attrRespVO = wineAttrMap.get(respVO.getWineId());
                    if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setVariety(listToEngStr(attrlist));
                    }
            }

            Map<Long, Img>  imgMap = imgMapperExt.selectImgMapByIds(imgIds);
            if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L){
                Img img = imgMap.get(respVO.getWineImgId());
                if(img!=null) {
                    respVO.setWineImgUrl(img.getImgUrl());
                    respVO.setWineSmallImgUrl(img.getSmallImgUrl());
                }
            }
            if(respVO.getWineryLogoId()!=null && respVO.getWineryLogoId()!=0L){
                Img logoImg = imgMap.get(respVO.getWineryLogoId());
                if(logoImg!=null) {
                    respVO.setWineryLogoUrl(logoImg.getImgUrl());
                    respVO.setWineryLogoSmallUrl(logoImg.getSmallImgUrl());
                }
            }
            if(respVO.getBannerImgId()!=null && respVO.getBannerImgId()!=0L){
                Img bannerImg = imgMap.get(respVO.getBannerImgId());
                if(bannerImg!=null) {
                    respVO.setBannerImgUrl(bannerImg.getImgUrl());
                    respVO.setBannerSmallImgUrl(bannerImg.getSmallImgUrl());
                }
            }
            if(!CollectionUtils.isEmpty(wineryImgIds)){
                List<String> wineryImgUrls = new ArrayList<String>();
                for(Long wineryImgId : wineryImgIds){
                    Img wineryImg = imgMap.get(wineryImgId);
                    if(wineryImg!=null) {
                        wineryImgUrls.add(wineryImg.getImgUrl());
                    }
                }
                respVO.setWineryImgUrls(wineryImgUrls);
            }
        }
    }

}