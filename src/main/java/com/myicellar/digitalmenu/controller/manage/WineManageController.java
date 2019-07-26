package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.service.ProductManageService;
import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
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

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/wine")
@Api(tags = "酒品管理", description = "/manage/wine")
public class WineManageController {

    @Autowired
    private WineService wineService;
    @Autowired
    private ProductManageService packageService;
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
        //参数校验
        checkNewParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO, Wine.class);
        String wineSeoName = wine.getWineNameEng().replaceAll(" ", "-").toLowerCase();
        wine.setWineSeoName(wineSeoName);
        wine.setWineId(snowflakeIdWorker.nextId());
        wine.setUpdatedAt(new Date());
        int i = wineService.insertSelective(wine);
        if (i == 0) {
            return ResultVO.validError("save is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine, WineRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO update(@RequestBody WineUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO, Wine.class);
        String wineSeoName = wine.getWineNameEng().replaceAll(" ", "-").toLowerCase();
        wine.setWineSeoName(wineSeoName);
        wine.setUpdatedAt(new Date());
        int i = wineService.updateByPrimaryKeySelective(wine);
        if (i == 0) {
            return ResultVO.validError("update is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine, WineRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO update(@RequestBody WineDeleteReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            return ResultVO.validError("parameter is invalid！");
        }
        IPackage iPackage = packageService.queryByWineId(reqVO.getWineId());
        if (iPackage != null) {
            return ResultVO.validError("Wine is in use, can not be deleted");
        }

        int i = wineService.deleteByPrimaryKey(reqVO.getWineId());
        if (i == 0) {
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(WineReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getWineNameEng())) {
            throw new BizException("wineNameEng can not be empty!");
        }
        if (reqVO.getWineTypeId() == null || reqVO.getWineTypeId() == 0L) {
            throw new BizException("wineTypeId can not be empty!");
        }
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId can not be empty!");
        }
        if (reqVO.getWineOriginId() == null || reqVO.getWineOriginId() == 0L) {
            throw new BizException("originId can not be empty!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(WineUpdateReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getWineNameEng())) {
            throw new BizException("wineNameEng can not be empty!");
        }
        if (reqVO.getWineTypeId() == null || reqVO.getWineTypeId() == 0L) {
            throw new BizException("wineTypeId can not be empty!");
        }
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId can not be empty!");
        }
        if (reqVO.getWineOriginId() == null || reqVO.getWineOriginId() == 0L) {
            throw new BizException("originId can not be empty!");
        }
        Wine wine = wineService.queryByName(reqVO.getWineNameEng());
        if (wine != null && !wine.getWineId().equals(reqVO.getWineId())) {
            throw new BizException("wineNameEng is already exists!");
        }
    }

}
