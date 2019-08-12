package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.IPackageMapperExt;
import com.myicellar.digitalmenu.dao.mapper.ProductMapperExt;
import com.myicellar.digitalmenu.dao.mapper.WineVintageMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.ProductManageReqVO;
import com.myicellar.digitalmenu.vo.request.ProductPageReqVO;
import com.myicellar.digitalmenu.vo.request.VolumPriceReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class ProductManageService extends BaseService<Long, Product, ProductMapperExt> {

    @Autowired
    private ImgService imgService;
    @Autowired
    private IPackageMapperExt iPackageMapperExt;

    @Autowired
    private WineVintageMapperExt wineVintageMapperExt;

    public Product selectBySupplierWineIdAndVintage(Long supplierId, Long wineId, Long vintageTag) {
        return mapper.selectBySupplierWineIdAndVintage(supplierId, wineId, vintageTag);
    }

    public Product selectByWineIdAndVintage(Long wineId, Long vintageTag) {
        return mapper.selectByWineIdAndVintage(wineId, vintageTag);
    }

    public ProductPriceRangeRespVO queryPriceRange(Long supplierId) {
        return iPackageMapperExt.selectPriceRange(supplierId);
    }

    public Product queryByWineId(Long wineId) {
        return mapper.selectByWineId(wineId);
    }

    /**
     * 供应商关联酒品列表查询-分页
     *
     * @param reqVO
     * @return
     */
    public PageResponseVO<ProductListRespVO> queryPageList(ProductPageReqVO reqVO) {
        PageResponseVO<ProductListRespVO> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            List<ProductListRespVO> list = page.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (ProductListRespVO respVO : list) {
                if (respVO.getWineImgId() != null && respVO.getWineImgId() != 0L) {
                    imgIds.add(respVO.getWineImgId());
                }
            }
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(respVO -> {
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
     * 新增供应商酒品关联
     *
     * @param reqVO
     * @return
     */
    @Transactional
    public Integer addNew(ProductManageReqVO reqVO) {
        Product tmpProduct = mapper.selectBySupplierWineIdAndVintage(reqVO.getSupplierId(),reqVO.getWineId(),reqVO.getVintageTag());
        if(tmpProduct!=null){
            throw new BizException("wineVintage is already exists!");
        }

        Product product = ConvertUtils.convert(reqVO, Product.class);
        Long productId = snowflakeIdWorker.nextId();
        product.setProductId(productId);
        product.setIsEnabled((byte) 1);
        Date now = new Date();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        product.setCreatedBy(1L);
        product.setUpdatedBy(1L);
        Integer result = mapper.insertSelective(product);

        if (result > 0) {
            Set<Long> volumeTypeIds = new HashSet<Long>();
            if (!CollectionUtils.isEmpty(reqVO.getVolumePrices())) {
                IPackage iPackage = new IPackage();
                iPackage.setProductId(productId);
                iPackage.setCreatedAt(now);
                iPackage.setUpdatedAt(now);
                iPackage.setCreatedBy(1L);
                iPackage.setUpdatedBy(1L);
                for (VolumPriceReqVO volumePrice : reqVO.getVolumePrices()) {
                    if(!volumeTypeIds.contains(volumePrice.getVolumeTypeId())) {
                        iPackage.setPackageId(snowflakeIdWorker.nextId());
                        iPackage.setVolumeTypeId(volumePrice.getVolumeTypeId());
                        iPackage.setRegularPrice(volumePrice.getPrice());
                        iPackageMapperExt.insertSelective(iPackage);
                        volumeTypeIds.add(volumePrice.getVolumeTypeId());
                    }
                }
            }
        }

        return result;
    }

    @Transactional
    public Integer update(ProductManageReqVO reqVO) {
        Integer result = 0;
        Product product = mapper.selectByPrimaryKey(reqVO.getProductId());
        if (product != null) {
            List<IPackage> packageList = iPackageMapperExt.selectListByProductId(product.getProductId());
            Set<Long> volumeTypeIds = new HashSet<Long>();
            Map<Long, Long> volumePkgMap = new HashMap<Long, Long>();
            if (!CollectionUtils.isEmpty(packageList)) {
                for (IPackage iPackage : packageList) {
                    volumeTypeIds.add(iPackage.getVolumeTypeId());
                    volumePkgMap.put(iPackage.getVolumeTypeId(), iPackage.getPackageId());
                }
            }
            Set<Long> newVolumeTypeIds = new HashSet<Long>();
            if (!CollectionUtils.isEmpty(reqVO.getVolumePrices())) {
                IPackage iPackage = new IPackage();
                iPackage.setProductId(product.getProductId());
                Date now = new Date();
                iPackage.setUpdatedAt(now);
                iPackage.setUpdatedBy(1L);
                for (VolumPriceReqVO volumePrice : reqVO.getVolumePrices()) {
                    newVolumeTypeIds.add(volumePrice.getVolumeTypeId());
                    iPackage.setRegularPrice(volumePrice.getPrice());
                    if (volumeTypeIds.contains(volumePrice.getVolumeTypeId())) {
                        iPackage.setPackageId(volumePkgMap.get(volumePrice.getVolumeTypeId()));
                        iPackageMapperExt.updateByPrimaryKeySelective(iPackage);
                    } else {
                        iPackage.setPackageId(snowflakeIdWorker.nextId());
                        iPackage.setVolumeTypeId(volumePrice.getVolumeTypeId());
                        iPackageMapperExt.insertSelective(iPackage);
                    }
                }

                for (Long volumeTypeId : volumeTypeIds) {
                    if (!newVolumeTypeIds.contains(volumeTypeId)) {
                        iPackageMapperExt.deleteByProductAndVolumeId(product.getProductId(), volumeTypeId);
                    }
                }
            }
            //更新是否推荐属性
            if(!reqVO.getIsRecommend().equals(product.getIsRecommend())) {
                Product updateProduct = new Product();
                updateProduct.setProductId(reqVO.getProductId());
                updateProduct.setIsRecommend(reqVO.getIsRecommend());
                mapper.updateByPrimaryKeySelective(updateProduct);
            }
        }
        result++;

        return result;
    }

    @Transactional
    public Integer deleteByProductId(Long productId) {
        Product product = mapper.selectByPrimaryKey(productId);
        if (product != null) {
            iPackageMapperExt.deleteByProductId(product.getProductId());
        }

        Integer result = mapper.deleteByPrimaryKey(productId);
        return result;
    }

    public ProductRespVO queryByProductId(Long productId) {
        ProductRespVO respVO = new ProductRespVO();
        Product product = mapper.selectByPrimaryKey(productId);
        if (product != null) {
            respVO = ConvertUtils.convert(product, ProductRespVO.class);
            List<IPackage> packageList = iPackageMapperExt.selectListByProductId(productId);
            if (!CollectionUtils.isEmpty(packageList)) {
                List<VolumPriceRespVO> volumePrices = new ArrayList<VolumPriceRespVO>();
                for (IPackage iPackage : packageList) {
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

    public List<VintageRespVO> queryVintageList(Long wineId){
        return wineVintageMapperExt.selectVintageList(wineId);
    }
}