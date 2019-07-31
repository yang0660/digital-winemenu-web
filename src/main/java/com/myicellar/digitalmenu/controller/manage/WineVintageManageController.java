package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.service.ProductManageService;
import com.myicellar.digitalmenu.service.WineVintageService;
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
    private ProductManageService productManageService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
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
    @ApiOperation("删除年份配置")
    public ResultVO<Integer> add(@RequestBody WineVintageInfoReqVO reqVO) {
        Product product = productManageService.selectByWineIdAndVintage(reqVO.getWineId(),reqVO.getVintageTag());
        if(product!=null){
            return ResultVO.validError("WineVintage is in use, can not be deleted");
        }

        Integer result = wineVintageService.delete(reqVO);

        return ResultVO.success(result);
    }

}
