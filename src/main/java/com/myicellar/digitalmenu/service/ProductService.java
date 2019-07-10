package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.ImgMapperExt;
import com.myicellar.digitalmenu.dao.mapper.ProductMapperExt;
import com.myicellar.digitalmenu.dao.mapper.WineAttrMapperExt;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import com.myicellar.digitalmenu.vo.response.ProductPriceRangeRespVO;
import com.myicellar.digitalmenu.vo.response.WineAttrInfoRespVO;
import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
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
public class ProductService extends BaseService<Long, Product, ProductMapperExt> {

    @Autowired
    private ImgMapperExt imgMapperExt;

    @Autowired
    private WineAttrMapperExt wineAttrMapperExt;

    /**
     * 列表查询
     * @return
     */
    public List<Product> queryList(){
        List<Product> list=mapper.selectList();
        return list;
    }

    public ProductPriceRangeRespVO queryPriceRange(Long supplierId){
        return mapper.selectPriceRange(supplierId);
    }

    /**
     * 查询供应商的推荐酒品列表
     * @param supplierId
     * @return
     */
    public List<ProductInfoRespVO> queryRecomendProductList(Long supplierId){
        List<ProductInfoRespVO>  list = mapper.selectRecomendProductList(supplierId);
        fillProductInfoRespVO(list);

        return list;
    }

    /**
     * 查询美食的酒品列表
     * @param foodId
     * @return
     */
    public List<ProductInfoRespVO> queryProductListByFoodId(Long foodId){
        List<ProductInfoRespVO>  list = mapper.selectProductListByFoodId(foodId);
        fillProductInfoRespVO(list);

        return list;
    }

    /**
     * 查询wishlist酒品列表
     * @param productIds
     * @return
     */
    public List<ProductInfoRespVO> queryProductListByIds(List<Long> productIds){
        List<ProductInfoRespVO>  list = mapper.selectProductListByIds(productIds);
        fillProductInfoRespVO(list);

        return list;
    }

    public void fillProductInfoRespVO(List<ProductInfoRespVO>  list){
        if(!CollectionUtils.isEmpty(list)){
            List<Long> imgIds = new ArrayList<Long>();
            List<Long> wineIds = new ArrayList<Long>();
            for(ProductInfoRespVO respVO : list){
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

}