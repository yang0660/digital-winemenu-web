package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.mapper.FoodMapperExt;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FoodService extends BaseService<Long, Food, FoodMapperExt> {

    @Autowired
    private ImgService imgService;

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<FoodRespVO> queryPageList(FoodPageReqVO reqVO) {
        PageResponseVO<Food> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        PageResponseVO<FoodRespVO> resultPage = new PageResponseVO<FoodRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,FoodRespVO.class);
            List<FoodRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for(FoodRespVO respVO : list){
                if(respVO.getFoodImgId()!=null && respVO.getFoodImgId()!=0L) {
                    imgIds.add(respVO.getFoodImgId());
                }
            }
            Map<Long,Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if(!CollectionUtils.isEmpty(imgMap)) {
                list.forEach(info -> {
                    if (info.getFoodImgId() != null && info.getFoodImgId() != 0L) {
                        Img img = imgMap.get(info.getFoodImgId());
                        if (img != null) {
                            info.setFoodImg(img.getImgUrl());
                        }
                    }
                });
            }

            resultPage.setItems(list);
        }
        return resultPage;
    }

    /**
     * 美食列表查询-根据美食分类id
     * @return
     */
    public List<FoodDisplayRespVO> queryListByFoodTypeId(Long foodTypeId){
        return mapper.selectListByFoodTypeId(foodTypeId);
    }

    /**
     * 美食详情查询(根据美食id)
     * @return
     */
    public FoodDetailRespVO queryDetailById(Long foodId){
        FoodDetailRespVO page = mapper.selectDetailById(foodId);
        return page;
    }

    /**
     * 首页推荐美食查询
     *
     * @return
     */
    public List<FoodRecommendRespVO> queryRecomendFoodList(SupplierIdReqVO reqVO) {
        return mapper.selectRecomendFoodList(reqVO);
    }

    /**
     * 查询酒品的推荐美食列表
     *
     * @return
     */
    public List<FoodRecommendRespVO> queryFoodListByProductId(Long productId) {
        return mapper.selectFoodListByProductId(productId);
    }
}