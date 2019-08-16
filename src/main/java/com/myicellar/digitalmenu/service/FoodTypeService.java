package com.myicellar.digitalmenu.service;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.mapper.FoodTypeMapperExt;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FoodTypeService extends BaseService<Long, FoodType, FoodTypeMapperExt> {

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    public void checkNewParam(FoodTypeReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplier cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodTypeNameEng())) {
            throw new BizException("foodTypeNameEng cannot be empty!");
        }
        if (queryByFoodTypeName(reqVO.getFoodTypeNameEng(),reqVO.getSupplierId()) != null) {
            throw new BizException("foodType already exists!");
        }
    }


    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    public void checkUpdateParam(FoodTypeReqVO reqVO) {
        if (reqVO.getFoodTypeId() == null || reqVO.getFoodTypeId() == 0L) {
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplier cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodTypeNameEng())) {
            throw new BizException("foodTypeNameEng cannot be empty!");
        }
        FoodType foodType = queryByFoodTypeName(reqVO.getFoodTypeNameEng(),reqVO.getSupplierId());
        if (foodType!=null && !foodType.getFoodTypeId().equals(reqVO.getFoodTypeId())) {
            throw new BizException("Food Type already exist!");
        }
    }

    @Transactional
    public synchronized ResultVO<Integer> addNew( FoodTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        FoodType foodType = ConvertUtils.convert(reqVO, FoodType.class);
        foodType.setFoodTypeId(snowflakeIdWorker.nextId());
        foodType.setCreatedBy(0L);
        foodType.setUpdatedBy(0L);
        Date now = new Date();
        foodType.setCreatedAt(now);
        foodType.setUpdatedAt(now);
        Integer result = mapper.insertSelective(foodType);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    @Transactional
    public ResultVO<Integer> update(FoodTypeReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        FoodType foodType = ConvertUtils.convert(reqVO, FoodType.class);
        foodType.setUpdatedBy(0L);
        Date now = new Date();
        foodType.setUpdatedAt(now);
        Integer result= mapper.updateByPrimaryKeySelective(foodType);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 列表查询-分页
     *
     * @return
     */
    public PageResponseVO<FoodType> queryPageList(FoodTypePageReqVO reqVO) {
        PageResponseVO<FoodType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }

    /**
     * 列表查询
     *
     * @return
     */
    public List<FoodType> queryListBysupplierId(Long supplierId) {
        return mapper.selectListBySupplierId(supplierId);
    }

    /**
     * 列表查询
     *
     * @return
     */
    public FoodType queryByFoodTypeName(String foodTypeNameEng,Long supplierId) {
        return mapper.selectByFoodTypeName(foodTypeNameEng,supplierId);
    }
}