package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.mapper.FoodMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.FoodDetailRespVO;
import com.myicellar.digitalmenu.vo.response.FoodDisplayRespVO;
import com.myicellar.digitalmenu.vo.response.FoodRecommendRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FoodService extends BaseService<Long, Food, FoodMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<Food> queryPageList(FoodPageReqVO reqVO){
        PageResponseVO<Food> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
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
    public List<FoodRecommendRespVO> queryFoodListByPackageId(Long packageId) {
        return mapper.selectFoodListByPackageId(packageId);
    }
}