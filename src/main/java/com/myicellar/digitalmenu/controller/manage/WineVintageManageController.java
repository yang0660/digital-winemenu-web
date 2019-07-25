package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.WineVintageService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineVintageInfoReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageListReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;
import com.myicellar.digitalmenu.vo.response.WineVintageRespVO;
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
@RequestMapping("/manage/winevtg")
@Api(tags = "酒品管理-年份管理", description = "/manage/winevtg")
public class WineVintageManageController {

    @Autowired
    private WineVintageService wineVintageService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<WineVintageListRespVO>> queryListPage(@RequestBody WineVintageListReqVO reqVO) {
        PageResponseVO<WineVintageListRespVO> page = wineVintageService.queryPageList(reqVO);

        return ResultVO.success(page);
    }

    /**
     * 年份详情
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByWineIdAndVintage")
    @AuthIgnore
    @ApiOperation("年份详情")
    public ResultVO<WineVintageRespVO> queryByWineIdAndVintage(@RequestBody WineVintageInfoReqVO reqVO) {
        WineVintageRespVO respVO = wineVintageService.queryByWineIdAndVintage(reqVO.getWineId(),
                reqVO.getVintageTag());

        return ResultVO.success(respVO);
    }

    /**
     * 添加年份配置
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("添加年份配置")
    public ResultVO<Integer> add(@RequestBody WineVintageReqVO reqVO) {
        Integer result = wineVintageService.addNew(reqVO);

        return ResultVO.success(result);
    }

    /**
     * 修改年份配置
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改年份配置")
    public ResultVO<Integer> update(@RequestBody WineVintageReqVO reqVO) {
        Integer result = wineVintageService.update(reqVO);

        return ResultVO.success(result);
    }

    /**
     * 删除年份配置
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除年份配置")
    public ResultVO<Integer> add(@RequestBody WineVintageInfoReqVO reqVO) {
        Integer result = wineVintageService.delete(reqVO);

        return ResultVO.success(result);
    }

}
