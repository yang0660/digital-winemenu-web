package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.FoodProductService;
import com.myicellar.digitalmenu.vo.request.FoodProductReqVO;
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
@RequestMapping("/manage/fdprd")
@Api(tags = "美食管理-关联酒品", description = "/manage/fdprd")
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
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody FoodProductReqVO reqVO) {
        return foodProductService.addNew(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO<Integer> delete(@RequestBody FoodProductReqVO reqVO) {
        return foodProductService.delete(reqVO);
    }
}
