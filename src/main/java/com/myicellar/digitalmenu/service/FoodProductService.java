package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.FoodProduct;
import com.myicellar.digitalmenu.dao.mapper.FoodProductMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.vo.request.FoodProductReqVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FoodProductService extends BaseService<Long, FoodProduct, FoodProductMapperExt> {

    /**
     * 校验参数
     *
     * @param reqVO
     */
    private void checkParam(FoodProductReqVO reqVO) {
        if (reqVO.getFoodId() == null || reqVO.getFoodId() == 0L) {
            throw new BizException("foodId cannot be empty!");
        }
        if (CollectionUtils.isEmpty(reqVO.getProductIds())) {
            throw new BizException("productIds cannot be empty!");
        }
    }

    /**
     * 新增美食酒品关联关系
     *
     * @return
     */
    @Transactional
    public synchronized ResultVO<Integer> addNew(FoodProductReqVO reqVO) {
        //参数校验
        checkParam(reqVO);

        Integer result = 0;
        List<Long> oldProductIds = mapper.selectListByFoodId(reqVO.getFoodId());
        List<Long> newProductIds = reqVO.getProductIds();
        if (!CollectionUtils.isEmpty(newProductIds)) {
            FoodProduct fp = new FoodProduct();
            fp.setFoodId(reqVO.getFoodId());
            fp.setCreatedBy(1L);
            fp.setUpdatedBy(1L);
            Date now = new Date();
            fp.setCreatedAt(now);
            fp.setUpdatedAt(now);
            for (Long productId : newProductIds) {
                if (!oldProductIds.contains(productId)) {
                    fp.setProductId(productId);
                    mapper.insertSelective(fp);
                    result++;
                }
            }
        }

        return ResultVO.success(result);
    }

    /**
     * 删除美食酒品关联关系
     *
     * @return
     */
    @Transactional
    public ResultVO<Integer> delete(FoodProductReqVO reqVO) {
        //参数校验
        checkParam(reqVO);

        Integer result = 0;
        List<Long> newProductIds = reqVO.getProductIds();
        if (!CollectionUtils.isEmpty(newProductIds)) {
            for (Long productId : newProductIds) {
                mapper.deleteByPrimaryKey(reqVO.getFoodId(), productId);
                result++;
            }
        }

        return ResultVO.success(result);
    }
}