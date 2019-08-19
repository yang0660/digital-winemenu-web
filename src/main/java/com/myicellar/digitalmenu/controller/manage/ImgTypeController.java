package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.ImgType;
import com.myicellar.digitalmenu.service.ImgTypeService;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.ImgTypeDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypePageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgTypeReqVO;
import com.myicellar.digitalmenu.vo.response.ImgTypeRespVO;
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
@RequestMapping("/manage/imgtype")
@Api(tags = "图库管理-图库分类", description = "/manage/imgtype")
public class ImgTypeController {

    @Autowired
    private ImgTypeService imgTypeService;

    /**
     * 图库分类下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryList")
    @ApiOperation("图库分类下拉列表")
    public ResultVO<List<ImgTypeRespVO>> queryList() {
        List<ImgType> list = imgTypeService.queryListAll();

        List<ImgTypeRespVO> resultList = new ArrayList<ImgTypeRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, ImgTypeRespVO.class);
        }

        return ResultVO.success(resultList);
    }

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<ImgTypeRespVO>> queryListPage(@RequestBody ImgTypePageReqVO reqVO) {
        PageResponseVO<ImgType> page = imgTypeService.queryPageList(reqVO);

        PageResponseVO<ImgTypeRespVO> resultPage = new PageResponseVO<ImgTypeRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, ImgTypeRespVO.class);
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
    public ResultVO<ImgTypeRespVO> add(@RequestBody ImgTypeReqVO reqVO) {
        return imgTypeService.addNew(reqVO);

    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO<ImgTypeRespVO> update(@RequestBody ImgTypeReqVO reqVO) {
        return imgTypeService.update(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO delete(@RequestBody ImgTypeDeleteReqVO reqVO) {
        return imgTypeService.delete(reqVO);
    }
}
