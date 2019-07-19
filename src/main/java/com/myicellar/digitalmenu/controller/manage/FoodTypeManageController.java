package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.FoodTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/foodtype")
@Api(tags = "美食管理-美食分类", description = "/manage/foodtype")
public class FoodTypeManageController {

    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 美食分类下拉列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("美食分类下拉列表")
    public ResultVO<List<FoodTypeRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {

        List<FoodType> list = foodTypeService.queryListBysupplierId(reqVO.getSupplierId());
        List<FoodTypeRespVO> resultList = new ArrayList<FoodTypeRespVO>();
        if(!CollectionUtils.isEmpty(list)){
            resultList = ConvertUtils.convert(list,FoodTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 列表查询-分页
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询-分页")
    public ResultVO<PageResponseVO<FoodTypeRespVO>> queryListPage(@RequestBody FoodTypePageReqVO reqVO) {
        PageResponseVO<FoodType> page = foodTypeService.queryPageList(reqVO);

        PageResponseVO<FoodTypeRespVO> resultPage = new PageResponseVO<FoodTypeRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,FoodTypeRespVO.class);
        }

        return ResultVO.success(resultPage);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<FoodTypeRespVO> add(@RequestBody FoodTypeReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        FoodType foodType = ConvertUtils.convert(reqVO,FoodType.class);
        foodType.setFoodTypeId(snowflakeIdWorker.nextId());
        foodType.setCreatedBy(0L);
        foodType.setUpdatedBy(0L);
        Date now = new Date();
        foodType.setCreatedAt(now);
        foodType.setUpdatedAt(now);
        int i = foodTypeService.insertSelective(foodType);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        FoodTypeRespVO respVO = ConvertUtils.convert(foodType,FoodTypeRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody FoodTypeReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        FoodType foodType = ConvertUtils.convert(reqVO,FoodType.class);
        foodType.setUpdatedBy(0L);
        Date now = new Date();
        foodType.setUpdatedAt(now);
        int i = foodTypeService.updateByPrimaryKeySelective(foodType);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        FoodTypeRespVO respVO = ConvertUtils.convert(foodType,FoodTypeRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody FoodTypeDeleteReqVO reqVO) {
        //参数校验
        checkDeleteParam(reqVO);
        int i = foodTypeService.deleteByPrimaryKey(reqVO.getFoodTypeId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    public void checkNewParam(FoodTypeReqVO reqVO){
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getFoodTypeNameEng())){
            throw new BizException("foodTypeNameEng cannot be empty!");
        }
        checkFoodTypeName(reqVO);
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    public void checkUpdateParam(FoodTypeReqVO reqVO){
        if(reqVO.getFoodTypeId()==null || reqVO.getFoodTypeId()==0L){
            throw new BizException("foodTypeId cannot be empty!");
        }
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getFoodTypeNameEng())){
            throw new BizException("foodTypeNameEng cannot be empty!");
        }
        checkFoodTypeName(reqVO);
    }

    /**
     * 校验删除参数
     * @param reqVO
     */
    public void checkDeleteParam(FoodTypeDeleteReqVO reqVO){
        if(reqVO.getFoodTypeId()==null || reqVO.getFoodTypeId()==0L){
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (foodService.queryListByFoodTypeId(reqVO.getFoodTypeId())!=null && !foodService.queryListByFoodTypeId(reqVO.getFoodTypeId()).isEmpty()) {
            throw new BizException("foodType is in use, can not be deleted!");
        }
    }

    /**
     * 校验美食分类名称是否已存在
     * @param reqVO
     */
    public void checkFoodTypeName(FoodTypeReqVO reqVO){
        List<FoodType> foodTypes = foodTypeService.queryListBysupplierId(reqVO.getSupplierId());
        foodTypes.forEach(foodType -> {if (foodType.getFoodTypeNameEng().equals(reqVO.getFoodTypeNameEng())){
            throw new BizException("foodTypeNameEng already exists!");
        }
        });
    }

}
