package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;
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
@RequestMapping("/manage/wine")
@Api(tags = "酒品管理", description = "/manage/wine")
public class WineManageController {

    @Autowired
    private WineService wineService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<WineRespVO>> queryListPage(@RequestBody WinePageReqVO reqVO) {
        PageResponseVO<WineRespVO> page = wineService.queryPageList(reqVO);

        return ResultVO.success(page);
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByWineId")
    @ApiOperation("详情查询")
    public ResultVO<WineRespVO> queryByWineId(@RequestBody WineDetailReqVO reqVO) {
        //通过传入wineId查询wine详情
        WineRespVO respVO = wineService.queryByWineId(reqVO.getWineId());
        if (reqVO == null) {
            respVO = new WineRespVO();
        }

        return ResultVO.success(respVO);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResultVO<WineRespVO> add(@RequestBody WineReqVO reqVO) {
        return wineService.addNew(reqVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO<WineRespVO> update(@RequestBody WineUpdateReqVO reqVO) {
        return wineService.update(reqVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO delete(@RequestBody WineDeleteReqVO reqVO) {
        return wineService.delete(reqVO);
    }
}
