package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.dao.mapper.FoodMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FoodService extends BaseService<Long, Food, FoodMapperExt> {

    @Autowired
    private ImgService imgService;

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(FoodReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        if (reqVO.getFoodTypeId() == null || reqVO.getFoodTypeId() == 0L) {
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodNameEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getFoodImgId() == null || reqVO.getFoodImgId() == 0L) {
            throw new BizException("foodImgId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("NotePlainEng cannot be empty!");
        }
        if (reqVO.getPrice() == null) {
            throw new BizException("price cannot be empty!");
        }
        if (reqVO.getIsRecommend() == null) {
            throw new BizException("isRecommend cannot be empty!");
        }
        if (queryFoodName(reqVO.getFoodNameEng(),reqVO.getSupplierId()) != null) {
            throw new BizException("Food already exist!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(FoodUpdateReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        if (reqVO.getFoodTypeId() == null || reqVO.getFoodTypeId() == 0L) {
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodNameEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getFoodImgId() == null || reqVO.getFoodImgId() == 0L) {
            throw new BizException("foodImgId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("NotePlainEng cannot be empty!");
        }
        if (reqVO.getPrice() == null) {
            throw new BizException("price cannot be empty!");
        }
        if (reqVO.getIsRecommend() == null) {
            throw new BizException("isRecommend cannot be empty!");
        }
        Food food = queryFoodName(reqVO.getFoodNameEng(),reqVO.getSupplierId());
        if (food!=null && !food.getFoodId().equals(reqVO.getFoodId())) {
            throw new BizException("Food already exist!");
        }
    }

    public synchronized ResultVO<Integer> addNew(FoodReqVO reqVO){
        //参数校验
        checkNewParam(reqVO);
        Food food = ConvertUtils.convert(reqVO, Food.class);
        food.setFoodId(snowflakeIdWorker.nextId());
        food.setCreatedBy(0L);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setCreatedAt(now);
        food.setUpdatedAt(now);
        Integer result= mapper.insertSelective(food);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    public ResultVO<Integer> update(FoodUpdateReqVO reqVO){
        //参数校验
        checkUpdateParam(reqVO);
        Food food = ConvertUtils.convert(reqVO, Food.class);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setUpdatedAt(now);
        Integer result = mapper.updateByPrimaryKeySelective(food);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);
    }

    public ResultVO<Integer> delete(FoodDeleteReqVO reqVO) {
        Integer result = mapper.deleteByPrimaryKey(reqVO.getFoodId());
        if (result == 0) {
            return ResultVO.validError("delete is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<FoodRespVO> queryPageList(FoodPageReqVO reqVO) {
        PageResponseVO<Food> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        PageResponseVO<FoodRespVO> resultPage = new PageResponseVO<FoodRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, FoodRespVO.class);
            List<FoodRespVO> list = resultPage.getItems();
            List<Long> imgIds = new ArrayList<Long>();
            for (FoodRespVO respVO : list) {
                if (respVO.getFoodImgId() != null && respVO.getFoodImgId() != 0L) {
                    imgIds.add(respVO.getFoodImgId());
                }
            }
            Map<Long, Img> imgMap = imgService.queryImgMapByIds(imgIds);
            if (!CollectionUtils.isEmpty(imgMap)) {
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
     *
     * @return
     */
    public List<FoodDisplayRespVO> queryListByFoodTypeId(Long foodTypeId) {
        return mapper.selectListByFoodTypeId(foodTypeId);
    }

    /**
     * 美食详情查询(根据美食id)
     *
     * @return
     */
    public FoodDetailRespVO queryDetailById(Long foodId) {
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

    /**
     * 后台美食详情查询(根据美食id)
     *
     * @return
     */
    public FoodDetailBGRespVO queryFoodDetail(Long foodId) {
        FoodDetailBGRespVO foodDetail = mapper.selectFoodDetail(foodId);
        Long imgId = foodDetail.getFoodImgId();
        if (imgId != null) {
            Img img = imgService.selectByPrimaryKey(imgId);
            try {
                if (img.getImgUrl() != null || img.getImgUrl().length() != 0) {
                    foodDetail.setImgUrl(img.getImgUrl());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foodDetail;
    }

    /**
     * 后台美食查询(根据美食名称)
     *
     * @return
     */
    public Food queryFoodName(String foodNameEng,Long supplierId) {
        return mapper.selectByFoodName(foodNameEng,supplierId);

    }
}