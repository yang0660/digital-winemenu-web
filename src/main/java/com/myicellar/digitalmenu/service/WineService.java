package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.*;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.dao.mapper.WineMapperExt;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineService extends BaseService<Long, Wine, WineMapperExt> {

    @Autowired
    private WineTypeService wineTypeService;
    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
    @Autowired
    private ImgMapperExt imgMapperExt;
    @Autowired
    private OriginService originService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private WineVintageService wineVintageService;
    @Autowired
    private WineVintageAttrService wineVintageAttrService;
    @Autowired
    private WineVintageScoreService wineVintageScoreService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PackageService packageService;

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<WineRespVO> queryPageList(WinePageReqVO reqVO){
        PageResponseVO<WineRespVO> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            List<WineRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(WineRespVO respVO : list){
                imgIds.add(respVO.getWineImgId());
            }

            Map<Long,Img> imgMap = imgMapperExt.selectImgMapByIds(imgIds);
            page.getItems().forEach(respVO ->{
                Img img = imgMap.get(respVO.getWineImgId());
                if(img!=null){
                    respVO.setWineImgUrl(img.getImgUrl());
                }
            });
        }

        return page;
    }

    public WineRespVO queryByWineId(Long wineId){
        WineRespVO respVO = mapper.selectByWineId(wineId);
        if(respVO!=null){
            if(respVO.getWineImgId()!=null) {
                Img img = imgMapperExt.selectByPrimaryKey(respVO.getWineImgId());
                if (img != null) {
                    respVO.setWineImgUrl(img.getImgUrl());
                }
            }
        }

        return respVO;
    }

    /**
     * 按酒庄ID查询
     * @return
     */
    public Wine queryByWineryId(Long wineryId){
        return mapper.selectByWineryId(wineryId);
    }

    public Wine queryByName(String wineNameEng){
        return mapper.selectByName(wineNameEng);
    }


    @Transactional
    public Integer importWineData(List<List<String>> lists){
        Integer n = 0;
        Map<String, Winery> wineryMap = wineryService.queryNameMap();
        Map<String, WineType> wineTypeMap = wineTypeService.queryNameMap();
        Map<String, Origin> originMap = originService.queryNameMap();
        Map<String, Attr> attrMap = attrService.queryNameMap();
        for(List<String> list : lists){
            //Winery	Wine Name	Vintage	Type	Region	Grape	Alcohol Level	Score 	Award	Taste Profile	Food Pairing
            // Tasting Note	Price per glass	Price per bottle
            log.info(list.toString());
            String wineryName = list.get(0);
            Winery winery = wineryMap.get(wineryName);
            Long wineryId = winery.getWineryId();
            String wineName = list.get(1);
            String vintage = list.get(2);
            String type = list.get(3);
            WineType wineType = wineTypeMap.get(type);
            Long wineTypeId = wineType.getWineTypeId();
            String vintageTag = vintage;
            if(vintage.equals("NV")){
                vintageTag = "1001";
            }
            String region = list.get(4);
            Origin origin = originMap.get(region.toLowerCase());
            Long originId = origin.getWineOriginId();
            String grap = list.get(5);
            String[] grapArr = grap.split(",");
            List<Attr> grapAttrs = new ArrayList<Attr>();
            if(grapArr.length>0)
                for(String grapStr : grapArr){
                    Attr attr = attrMap.get(grapStr);
                    if(attr!=null){
                        grapAttrs.add(attr);
                    }
                }

            String alcohol = list.get(6);
            Long alcohol_bps = new BigDecimal(alcohol).multiply(new BigDecimal(100)).longValue();
            String score = list.get(7);
            String award = list.get(8);
            String tastingProfile = list.get(9);
            String foodPairing = list.get(10);
            String tastingNote = list.get(11);
            String glassPrice = "88.00";//list.get(12);
            String regularPrice = "268.00";//list.get(13);

            if(wineryName!=null && wineryName.trim().length()!=0) {
                Wine wine = new Wine();
                Long wineId = snowflakeIdWorker.nextId();
                wine.setWineId(wineId);
                wine.setWineryId(wineryId);
                wine.setWineTypeId(wineTypeId);
                wine.setWineOriginId(originId);
                wine.setWineNameEng(wineName);
                wine.setWineSeoName(wineName.toLowerCase());
                Date now = new Date();
                wine.setUpdatedAt(now);
                //添加酒品
                wineService.insertSelective(wine);
                //添加年份属性
                WineVintage wineVintage = new WineVintage();
                wineVintage.setWineId(wineId);
                wineVintage.setVintageTag(new BigDecimal(vintageTag).longValue());
                wineVintage.setVintageName(vintage);
                wineVintage.setAlcoholBps(alcohol_bps);
                wineVintage.setTastingNote(tastingNote);
                wineVintage.setUpdatedAt(now);
                wineVintageService.insertSelective(wineVintage);

                //添加葡萄属性
                WineVintageAttr wineVintageAttr = new WineVintageAttr();
                wineVintageAttr.setWineId(wineId);
                wineVintageAttr.setVintageTag(new BigDecimal(vintageTag).longValue());
                wineVintageAttr.setMicRank((short)9999);
                wineVintageAttr.setUpdatedAt(now);
                for(Attr attrTmp : grapAttrs){
                    wineVintageAttr.setAttrId(attrTmp.getAttrId());
                    wineVintageAttrService.insertSelective(wineVintageAttr);
                }

                //添加评分及获奖
                WineVintageScore wineVintageScore = new WineVintageScore();
                wineVintageScore.setVintageScoreId(snowflakeIdWorker.nextId());
                wineVintageScore.setWineId(wineId);
                wineVintageScore.setVintageTag(new BigDecimal(vintageTag).shortValue());
                wineVintageScore.setNotePlain(tastingNote);
                wineVintageScore.setWineCriticsId(104);
                wineVintageScore.setScoreCatgId(0L);
                wineVintageScore.setScoreValNum(Short.valueOf("950"));
                wineVintageScore.setScoreValStr("95");
                wineVintageScore.setTastedAt(now);
                wineVintageScore.setUpdatedAt(now);
                wineVintageScoreService.insertSelective(wineVintageScore);
                //添加product
                Product product = new Product();
                Long productId = snowflakeIdWorker.nextId();
                product.setProductId(productId);
                product.setWineId(wineId);
                product.setVintageTag(new BigDecimal(vintageTag).longValue());
                product.setIsEnabled((byte)1);
                product.setSupplierId(10000L);
                product.setIsRecommend((byte)0);
                productService.insertSelective(product);
                //添加package
                IPackage iPackage = new IPackage();
                iPackage.setPackageId(snowflakeIdWorker.nextId());
                iPackage.setProductId(productId);
                iPackage.setRegularPrice(new BigDecimal(regularPrice));
                iPackage.setGlassPrice(new BigDecimal(glassPrice));
                iPackage.setCreatedAt(now);
                iPackage.setUpdatedAt(now);
                iPackage.setCreatedBy(1L);
                iPackage.setUpdatedBy(1L);
                packageService.insertSelective(iPackage);

                n++;
            }
        }

        return n;
    }


}