package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.utils.ConvertUtils;
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
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/foodtype")
@Api(tags = "美食管理-美食分类", description = "/manage/foodtype")
public class FoodTypeManageController {

    @Autowired
    private FoodTypeService foodTypeService;

    /**
     * 美食分类下拉列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryList")
    @ApiOperation("美食分类下拉列表")
    public ResultVO<List<FoodTypeRespVO>> queryList(@RequestBody SupplierIdReqVO reqVO) {

        List<FoodType> list = foodTypeService.queryListBysupplierId(reqVO.getSupplierId());
        List<FoodTypeRespVO> resultList = new ArrayList<FoodTypeRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, FoodTypeRespVO.class);
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
    @ApiOperation("列表查询-分页")
    public ResultVO<PageResponseVO<FoodTypeRespVO>> queryListPage(@RequestBody FoodTypePageReqVO reqVO) {
        PageResponseVO<FoodType> page = foodTypeService.queryPageList(reqVO);

        PageResponseVO<FoodTypeRespVO> resultPage = new PageResponseVO<FoodTypeRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, FoodTypeRespVO.class);
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
    @ApiOperation("新增")
    public ResultVO add(@RequestBody FoodTypeReqVO reqVO) {
        return foodTypeService.addNew(reqVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO update(@RequestBody FoodTypeReqVO reqVO) {
        return foodTypeService.update(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO delete(@RequestBody FoodTypeDeleteReqVO reqVO) {
        return foodTypeService.delete(reqVO);
    }
}
