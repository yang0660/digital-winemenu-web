package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.entity.FoodProduct;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.dao.mapper.FoodProductMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodProductpageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ProductRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodProductService extends BaseService<Long, FoodProduct, FoodProductMapperExt>{

    /**
     * 美食-产品关联关系查询-分页
     * @return
     */
    public PageResponseVO<FoodProduct> queryPageList(FoodProductpageReqVO reqVO){
        PageResponseVO<FoodProduct> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }


    /**
     * 根据foodId查询product列表
     * @return
     */
    public PageResponseVO<Product> queryProductPage(FoodProductpageReqVO reqVO){
        PageResponseVO<Product> page =selectPage(reqVO, mapper::selectProductCount, mapper::selectProductList);
        return page;
    }


    /**
     * 根据foodId查询product列表
     * @return
     */
    public PageResponseVO<Food> queryFoodPage(FoodProductpageReqVO reqVO){
        PageResponseVO<Food> page =selectPage(reqVO, mapper::selectFoodCount, mapper::selectFoodList);
        return page;
    }

}
