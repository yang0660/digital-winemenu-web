package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.FoodDetailBGRespVO;
import com.myicellar.digitalmenu.vo.response.FoodRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manage/food")
@Api(tags = "美食管理-美食", description = "/manage/food")
public class FoodManageController {

    @Autowired
    private FoodService foodService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<FoodRespVO>> queryListPage(@RequestBody FoodPageReqVO reqVO) {
        PageResponseVO<FoodRespVO> page = foodService.queryPageList(reqVO);
        return ResultVO.success(page);
    }

    /**
     * 后台美食详情
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryFoodDetail")
    @ApiOperation("后台美食详情")
    public ResultVO<FoodDetailBGRespVO> queryFoodDetail(@RequestBody FoodDetailReqVO reqVO) {
        FoodDetailBGRespVO result = foodService.queryFoodDetail(reqVO.getFoodId());
        return ResultVO.success(result);
    }


    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResultVO add(@RequestBody FoodReqVO reqVO) {
        return foodService.addNew(reqVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO update(@RequestBody FoodUpdateReqVO reqVO) {
        return foodService.update(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO delete(@RequestBody FoodDeleteReqVO reqVO) {
        return foodService.delete(reqVO);
    }
}
