package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Img;
import com.myicellar.digitalmenu.service.ImgService;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.ImgBatchDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ImgPageReqVO;
import com.myicellar.digitalmenu.vo.request.ImgReqVO;
import com.myicellar.digitalmenu.vo.response.ImgRespVO;
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

@RestController
@Slf4j
@RequestMapping("/manage/img")
@Api(tags = "图库管理-图片管理", description = "/manage/img")
public class ImgController {

    @Autowired
    private ImgService imgService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<ImgRespVO>> queryListPage(@RequestBody ImgPageReqVO reqVO) {
        PageResponseVO<Img> page = imgService.queryPageList(reqVO);

        PageResponseVO<ImgRespVO> resultPage = new PageResponseVO<ImgRespVO>();
        if (page != null && !CollectionUtils.isEmpty(page.getItems())) {
            resultPage = ConvertUtils.convertPage(page, ImgRespVO.class);
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
    public ResultVO<ImgRespVO> add(@RequestBody ImgReqVO reqVO) {
        return imgService.addNew(reqVO);
    }

    /**
     * 批量删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/batchDelete")
    @ApiOperation("批量删除")
    public ResultVO batchDelete(@RequestBody ImgBatchDeleteReqVO reqVO) {
        return imgService.batchDelete(reqVO);
    }
}
