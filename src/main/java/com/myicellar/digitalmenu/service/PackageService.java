package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.IPackageMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.PackageReqVO;
import com.myicellar.digitalmenu.vo.request.VolumPriceReqVO;
import com.myicellar.digitalmenu.vo.response.PackageRespVO;
import com.myicellar.digitalmenu.vo.response.ProductPriceRangeRespVO;
import com.myicellar.digitalmenu.vo.response.VolumPriceRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class PackageService extends BaseService<Long, IPackage, IPackageMapperExt> {

    @Autowired
    private ImgService imgService;
    @Autowired
    private ProductService productService;

    public ProductPriceRangeRespVO queryPriceRange(Long supplierId){
        return mapper.selectPriceRange(supplierId);
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
}