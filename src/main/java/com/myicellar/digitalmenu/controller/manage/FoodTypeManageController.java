package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.FoodTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
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

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/foodtype")
@Api(tags = "美食分类", description = "/manage/foodtype")
public class FoodTypeManageController {

    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
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
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO add(@RequestBody FoodTypeReqVO reqVO) {
        FoodType foodType = ConvertUtils.convert(reqVO,FoodType.class);
        foodType.setFoodTypeId(snowflakeIdWorker.nextId());
        foodType.setCreateUser(0L);
        foodType.setUpdateUser(0L);
        Date now = new Date();
        foodType.setCreateTime(now);
        foodType.setUpdateTime(now);
        int i = foodTypeService.insertSelective(foodType);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        return ResultVO.success("save is success!");
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody FoodTypeReqVO reqVO) {
        FoodType foodType = ConvertUtils.convert(reqVO,FoodType.class);
        foodType.setUpdateUser(0L);
        Date now = new Date();
        foodType.setUpdateTime(now);
        int i = foodTypeService.updateByPrimaryKeySelective(foodType);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        return ResultVO.success("update is success!");
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody FoodTypeDeleteReqVO reqVO) {
        int i = foodTypeService.deleteByPrimaryKey(reqVO.getFoodTypeId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

}
