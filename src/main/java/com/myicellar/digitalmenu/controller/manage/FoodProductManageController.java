package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.FoodProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.vo.request.FoodProductReqVO;
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

@RestController
@Slf4j
@RequestMapping("/manage/fdprd")
@Api(tags = "美食管理-美食", description = "/manage/fdprd")
public class FoodProductManageController {

    @Autowired
    private FoodProductService foodProductService;

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody FoodProductReqVO reqVO) {
        //参数校验
        checkParam(reqVO);
        return ResultVO.success(foodProductService.addNew(reqVO));
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
    public ResultVO<Integer> update(@RequestBody FoodProductReqVO reqVO) {
        //参数校验
        checkParam(reqVO);
        return ResultVO.success(foodProductService.delete(reqVO));
    }

    /**
     * 校验参数
     *
     * @param reqVO
     */
    private void checkParam(FoodProductReqVO reqVO) {
        if (reqVO.getFoodId() == null || reqVO.getFoodId() == 0L) {
            throw new BizException("foodId cannot be empty!");
        }
        if (!CollectionUtils.isEmpty(reqVO.getProductIds())) {
            throw new BizException("productIds cannot be empty!");
        }
    }
}
