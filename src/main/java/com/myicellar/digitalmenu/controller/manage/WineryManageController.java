package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.service.WineryService;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineryDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineryDetailReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.request.WineryReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineryRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/winery")
@Api(tags = "酒庄管理", description = "/manage/winery")
public class WineryManageController {

    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 酒庄下拉列表
     *
     * @return
     */
    @PostMapping(value = "/queryList")
    @ApiOperation("酒庄下拉列表")
    public ResultVO<List<WineryRespVO>> queryList() {
        List<Winery> list = wineryService.queryListAll();

        List<WineryRespVO> resultList = new ArrayList<WineryRespVO>();
        if (!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, WineryRespVO.class);
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
    public ResultVO<PageResponseVO<WineryRespVO>> queryListPage(@RequestBody WineryPageReqVO reqVO) {
        PageResponseVO<WineryRespVO> page = wineryService.queryPageList(reqVO);
        return ResultVO.success(page);
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByWineryId")
    @ApiOperation("详情查询")
    public ResultVO<WineryRespVO> queryByWineryId(@RequestBody WineryDetailReqVO reqVO) {

        WineryRespVO respVO = wineryService.queryByWineryId(reqVO);

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
    public ResultVO<Integer> add(@RequestBody WineryReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);

        Winery winery = ConvertUtils.convert(reqVO, Winery.class);
        winery.setWineryId(snowflakeIdWorker.nextId());
        winery.setCreatedBy(0L);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setCreatedAt(now);
        winery.setUpdatedAt(now);
        List<Long> longIds = reqVO.getWineryImgIds();
        String stringIds = StringUtils.join(longIds, ",");
        winery.setWineryImgIds(stringIds);
        Integer result= wineryService.insertSelective(winery);
        if (result == 0) {
            return ResultVO.validError("save is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    public ResultVO<Integer> update(@RequestBody WineryReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Winery winery = ConvertUtils.convert(reqVO, Winery.class);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setUpdatedAt(now);
        List<Long> longIds = reqVO.getWineryImgIds();
        String stringIds = StringUtils.join(longIds, ",");
        winery.setWineryImgIds(stringIds);
        Integer result= wineryService.updateByPrimaryKeySelective(winery);
        if (result == 0) {
            return ResultVO.validError("update is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO<Integer> update(@RequestBody WineryDeleteReqVO reqVO) {
        checkDeleteParam(reqVO);
        Integer result= wineryService.deleteByPrimaryKey(reqVO.getWineryId());
        if (result == 0) {
            return ResultVO.validError("delete is failed!");
        }
        return ResultVO.success(result);
    }

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(WineryReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getWineryNameEng())) {
            throw new BizException("wineryNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0) {
            throw new BizException("winery Logo cannot be empty!");
        }
        if (reqVO.getBannerImgId() == null || reqVO.getBannerImgId() == 0) {
            throw new BizException("winery Banner cannot be empty!");
        }
        if (reqVO.getWineryImgIds().isEmpty()) {
            throw new BizException("winery Image cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getAboutUrl())) {
            throw new BizException("Url cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("Note cannot be empty!");
        }

        //判断酒庄名字是否已经存在
        Winery winery = wineryService.queryByName(reqVO.getWineryNameEng());
        if (winery != null) {
            throw new BizException("winery already exists");
        }

    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(WineryReqVO reqVO) {
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("wineryId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getWineryNameEng())) {
            throw new BizException("wineryNameEng cannot be empty!");
        }
        if (reqVO.getLogoImgId() == null || reqVO.getLogoImgId() == 0) {
            throw new BizException("winery Logo cannot be empty!");
        }
        if (reqVO.getBannerImgId() == null || reqVO.getBannerImgId() == 0) {
            throw new BizException("winery Banner cannot be empty!");
        }
        if (reqVO.getWineryImgIds().isEmpty()) {
            throw new BizException("winery Image cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getAboutUrl())) {
            throw new BizException("Url cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("Note cannot be empty!");
        }

        Winery winery = wineryService.queryByName(reqVO.getWineryNameEng());
        if (winery != null && !winery.getWineryId().equals(reqVO.getWineryId())) {
            throw new BizException("winery already exists");
        }
    }

    /**
     * 校验删除参数
     *
     * @param reqVO
     */
    private void checkDeleteParam(WineryDeleteReqVO reqVO) {
        if (reqVO.getWineryId() == null || reqVO.getWineryId() == 0L) {
            throw new BizException("parameter is invalid！");
        }
        //如果酒庄里有关联酒品, 无法删除酒庄
        if (wineService.queryByWineryId(reqVO.getWineryId()) != null) {
            throw new BizException("Winery is in use, can not be deleted");
        }
    }


}
