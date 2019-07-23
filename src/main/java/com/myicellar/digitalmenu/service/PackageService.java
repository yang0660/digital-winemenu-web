package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.IPackageMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.request.PackageReqVO;
import com.myicellar.digitalmenu.vo.request.VolumPriceReqVO;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class PackageService extends BaseService<Long, IPackage, IPackageMapperExt> {

    @Autowired
    private ImgService imgService;
    @Autowired
    private WineVintageAttrService wineVintageAttrService;
    @Autowired
    private ProductService productService;

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
            List<String> wineVintageIds = new ArrayList<String>();
            for(PackageInfoRespVO respVO : list){
                wineVintageIds.add(respVO.getWineId()+"|"+respVO.getVintageTag());
                if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L){
                    imgIds.add(respVO.getWineImgId());
                }
                if(respVO.getWineryLogoId()!=null && respVO.getWineryLogoId()!=0L){
                    imgIds.add(respVO.getWineryLogoId());
                }
            }

            Map<String,WineAttrMapRespVO> wineAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(101L,wineVintageIds);
            if(!CollectionUtils.isEmpty(wineAttrMap)){
                list.forEach(info -> {
                    WineAttrMapRespVO respVO = wineAttrMap.get(info.getWineId()+"|"+info.getVintageTag());
                    if(respVO!=null && !CollectionUtils.isEmpty(respVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = respVO.getList();
                        info.setVariety(wineVintageAttrService.listToEngStr(attrlist));
                    }
                });
            }

            Map<Long, Img>  imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)){
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
            List<String> wineVintageIds = new ArrayList<String>();
            wineVintageIds.add(respVO.getWineId()+"|"+respVO.getVintageTag());
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
            //原料
            Map<String,WineAttrMapRespVO> varietyAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(101L,wineVintageIds);
            if(!CollectionUtils.isEmpty(varietyAttrMap)){
                    WineAttrMapRespVO attrRespVO = varietyAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                    if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                        List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                        respVO.setVariety(wineVintageAttrService.listToEngStr(attrlist));
                    }
            }
            //风格
            Map<String,WineAttrMapRespVO> styleAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1001L,wineVintageIds);
            if(!CollectionUtils.isEmpty(styleAttrMap)){
                WineAttrMapRespVO attrRespVO = styleAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                    List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                    respVO.setStyle(wineVintageAttrService.listToEngStr(attrlist));
                }
            }
            //口味
            Map<String,WineAttrMapRespVO> descriptorAttrMap = wineVintageAttrService.queryAttrMapByWineVintageIds(1002L,wineVintageIds);
            if(!CollectionUtils.isEmpty(descriptorAttrMap)){
                WineAttrMapRespVO attrRespVO = descriptorAttrMap.get(respVO.getWineId()+"|"+respVO.getVintageTag());
                if(respVO!=null && !CollectionUtils.isEmpty(attrRespVO.getList())){
                    List<WineAttrInfoRespVO> attrlist = attrRespVO.getList();
                    respVO.setDescriptor(wineVintageAttrService.listToEngStr(attrlist));
                }
            }

            Map<Long, Img>  imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)) {
                if (respVO.getWineImgId() != null && respVO.getWineImgId() != 0L) {
                    Img img = imgMap.get(respVO.getWineImgId());
                    if (img != null) {
                        respVO.setWineImgUrl(img.getImgUrl());
                        respVO.setWineSmallImgUrl(img.getSmallImgUrl());
                    }
                }
                if (respVO.getWineryLogoId() != null && respVO.getWineryLogoId() != 0L) {
                    Img logoImg = imgMap.get(respVO.getWineryLogoId());
                    if (logoImg != null) {
                        respVO.setWineryLogoUrl(logoImg.getImgUrl());
                        respVO.setWineryLogoSmallUrl(logoImg.getSmallImgUrl());
                    }
                }
                if (respVO.getBannerImgId() != null && respVO.getBannerImgId() != 0L) {
                    Img bannerImg = imgMap.get(respVO.getBannerImgId());
                    if (bannerImg != null) {
                        respVO.setBannerImgUrl(bannerImg.getImgUrl());
                        respVO.setBannerSmallImgUrl(bannerImg.getSmallImgUrl());
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
    }

    public IPackage queryByWineId(Long wineId){
        return mapper.selectByWineId(wineId);
    }

    /**
     * 新增供应商酒品关联
     * @param reqVO
     * @return
     */
    @Transactional
    public Integer addNew(PackageReqVO reqVO){
        Product product = ConvertUtils.convert(reqVO, Product.class);
        Long productId = snowflakeIdWorker.nextId();
        product.setProductId(productId);
        product.setIsEnabled((byte)1);
        Date now = new Date();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product.setCreatedBy(1L);
        product.setUpdatedBy(1L);
        Integer result = productService.insertSelective(product);
        if(result > 0){
            if(!CollectionUtils.isEmpty(reqVO.getVolumePrices())){
                IPackage iPackage = new IPackage();
                iPackage.setProductId(productId);
                iPackage.setCreatedAt(now);
                iPackage.setUpdatedAt(now);
                iPackage.setCreatedBy(1L);
                iPackage.setUpdatedBy(1L);
                for(VolumPriceReqVO volumePrice : reqVO.getVolumePrices()){
                    iPackage.setPackageId(snowflakeIdWorker.nextId());
                    iPackage.setVolumeTypeId(volumePrice.getVolumeTypeId());
                    iPackage.setRegularPrice(volumePrice.getPrice());
                    mapper.insertSelective(iPackage);
                }
            }
        }

        return result;
    }

    @Transactional
    public Integer update(PackageReqVO reqVO){
        Integer result = 0;
        Product product = productService.selectByPrimaryKey(reqVO.getProductId());
        if(product!=null){
            List<IPackage> packageList = mapper.selectListByProductId(product.getProductId());
            List<Long> volumeTypeIds = new ArrayList<>();
            Map<Long,Long> volumePkgMap = new HashMap<Long,Long>();
            if(!CollectionUtils.isEmpty(packageList)) {
                for(IPackage iPackage : packageList){
                    volumeTypeIds.add(iPackage.getVolumeTypeId());
                    volumePkgMap.put(iPackage.getVolumeTypeId(),iPackage.getPackageId());
                }
            }
            List<Long> newVolumeTypeIds = new ArrayList<>();
            if(!CollectionUtils.isEmpty(reqVO.getVolumePrices())){
                IPackage iPackage = new IPackage();
                iPackage.setProductId(product.getProductId());
                Date now = new Date();
                iPackage.setUpdatedAt(now);
                iPackage.setUpdatedBy(1L);
                for(VolumPriceReqVO volumePrice : reqVO.getVolumePrices()){
                    newVolumeTypeIds.add(volumePrice.getVolumeTypeId());
                    if(volumeTypeIds.contains(volumePrice.getVolumeTypeId())){
                        iPackage.setPackageId(volumePkgMap.get(volumePrice.getVolumeTypeId()));
                    }else {
                        iPackage.setPackageId(snowflakeIdWorker.nextId());
                        iPackage.setVolumeTypeId(volumePrice.getVolumeTypeId());
                    }
                    iPackage.setRegularPrice(volumePrice.getPrice());

                    if(volumeTypeIds.contains(volumePrice.getVolumeTypeId())){
                        mapper.updateByPrimaryKeySelective(iPackage);
                    }else {
                        mapper.insertSelective(iPackage);
                    }
                }

                for(Long volumeTypeId : volumeTypeIds){
                    if(!newVolumeTypeIds.contains(volumeTypeId)){
                        mapper.deleteByProductAndVolumeId(product.getProductId(),volumeTypeId);
                    }
                }
            }else{
                productService.deleteByPrimaryKey(product.getProductId());
            }
        }
        result++;

        return result;
    }

    @Transactional
    public Integer deleteByProductId(Long productId){
        Product product = productService.selectByPrimaryKey(productId);
        if(product!=null) {
            mapper.deleteByProductId(product.getProductId());
        }

        Integer result = productService.deleteByPrimaryKey(productId);
        return result;
    }

    public PackageRespVO queryByProductId(Long productId){
        PackageRespVO respVO = new PackageRespVO();
        Product product = productService.selectByPrimaryKey(productId);
        if(product!=null){
            respVO = ConvertUtils.convert(product,PackageRespVO.class);
            List<IPackage> packageList = mapper.selectListByProductId(productId);
            if(!CollectionUtils.isEmpty(packageList)){
                List<VolumPriceRespVO> volumePrices = new ArrayList<VolumPriceRespVO>();
                for(IPackage iPackage : packageList){
                    VolumPriceRespVO volumPriceRespVO = new VolumPriceRespVO();
                    volumPriceRespVO.setVolumeTypeId(iPackage.getVolumeTypeId());
                    volumPriceRespVO.setPrice(iPackage.getRegularPrice());
                    volumePrices.add(volumPriceRespVO);
                }
                respVO.setVolumePrices(volumePrices);
            }
        }

        return respVO;
    }

    /**
     * 供应商关联酒品列表查询-分页
     * @param reqVO
     * @return
     */
    public PageResponseVO<ProductListRespVO> queryPageList(WinePageReqVO reqVO){
        PageResponseVO<ProductListRespVO> page = selectPage(reqVO,mapper:: selectCount, mapper:: selectList);

        if(page!=null && !CollectionUtils.isEmpty(page.getItems())) {
            List<ProductListRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(ProductListRespVO respVO : list){
                if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L) {
                    imgIds.add(respVO.getWineImgId());
                }
            }
            Map<Long,Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(respVO ->{
                    if (respVO.getWineImgId() != null && respVO.getWineImgId() != 0L) {
                        Img img = imgMap.get(respVO.getWineImgId());
                        if (img != null) {
                            respVO.setWineImgUrl(img.getImgUrl());
                        }
                    }
                });

            }

            page.setItems(list);
        }

        return page;
    }

    /**
     * 供应商关联酒品列表查询-分页
     * @param reqVO
     * @return
     */
    public PageResponseVO<PackageListRespVO> queryPackagePageList(WinePageReqVO reqVO){
        PageResponseVO<PackageListRespVO> page = selectPage(reqVO,mapper:: selectPackageCount, mapper:: selectPackageList);

        if(page!=null && !CollectionUtils.isEmpty(page.getItems())) {
            List<PackageListRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(PackageListRespVO respVO : list){
                if(respVO.getWineImgId()!=null && respVO.getWineImgId()!=0L) {
                    imgIds.add(respVO.getWineImgId());
                }
            }
            Map<Long,Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(respVO ->{
                    if (respVO.getWineImgId() != null && respVO.getWineImgId() != 0L) {
                        Img img = imgMap.get(respVO.getWineImgId());
                        if (img != null) {
                            respVO.setWineImgUrl(img.getImgUrl());
                        }
                    }
                });

            }

            page.setItems(list);
        }

        return page;
    }
}