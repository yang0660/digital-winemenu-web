package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Winery;
import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.service.WineryService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineryDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineryNameReqVO;
import com.myicellar.digitalmenu.vo.request.WineryPageReqVO;
import com.myicellar.digitalmenu.vo.request.WineryReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineryRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/winery")
@Api(tags = "酒庄", description = "/manage/winery")
public class WineryManageController {

    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
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
    public ResultVO<PageResponseVO<WineryRespVO>> queryListPage(@RequestBody WineryPageReqVO reqVO) {
        PageResponseVO<Winery> page = wineryService.queryPageList(reqVO);

        PageResponseVO<WineryRespVO> resultPage = new PageResponseVO<WineryRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineryRespVO.class);
        }

        return ResultVO.success(resultPage);
    }

    /**
     * 详情查询-按酒庄名称
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByName")
    @AuthIgnore
    @ApiOperation("详情查询-按酒庄名称")
    public ResultVO<PageResponseVO<WineryRespVO>> queryByName(@RequestBody WineryNameReqVO reqVO) {
        PageResponseVO<Winery> page = wineryService.queryByName(reqVO);
        PageResponseVO<WineryRespVO> resultPage = new PageResponseVO<WineryRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineryRespVO.class);
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
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<WineryRespVO> add(@RequestBody WineryReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Winery winery = ConvertUtils.convert(reqVO,Winery.class);
        winery.setWineryId(snowflakeIdWorker.nextId());
        winery.setCreatedBy(0L);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setCreatedAt(now);
        winery.setUpdatedAt(now);
        int i = wineryService.insertSelective(winery);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineryRespVO respVO = ConvertUtils.convert(winery,WineryRespVO.class);
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
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody WineryReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Winery winery = ConvertUtils.convert(reqVO,Winery.class);
        winery.setUpdatedBy(0L);
        Date now = new Date();
        winery.setUpdatedAt(now);
        int i = wineryService.updateByPrimaryKeySelective(winery);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineryRespVO respVO = ConvertUtils.convert(winery,WineryRespVO.class);
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
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody WineryDeleteReqVO reqVO) {
        if(reqVO.getWineryId()==null || reqVO.getWineryId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        //如果酒庄里有关联酒品, 无法删除酒庄
        if (wineService.queryByWineryId(reqVO.getWineryId())!=null){
            return ResultVO.validError("delete is failed! winery has wine in it.");
        }
        int i = wineryService.deleteByPrimaryKey(reqVO.getWineryId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineryReqVO reqVO){
        if(reqVO.getWineryId()==null || reqVO.getWineryId()==0L){
            throw new BizException("winery cannot be empty!");
        }
        //判断酒庄名字是否已经存在
        this.checkWineyName(reqVO.getWineryNameEng());
        if(StringUtils.isEmpty(reqVO.getWineryNameEng())){
            throw new BizException("wineryNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineryReqVO reqVO){
        if(reqVO.getWineryId()==null || reqVO.getWineryId()==0L){
            throw new BizException("wineryId cannot be empty!");
        }
        //判断酒庄名字是否已经存在
        this.checkWineyName(reqVO.getWineryNameEng());
        if(StringUtils.isEmpty(reqVO.getWineryNameEng())){
            throw new BizException("wineryNameEng cannot be empty!");
        }
    }

    /**
     * 判断酒庄名字是否存在
     * @param wineryName
     */
    private void checkWineyName(String wineryName){
        //判断酒庄名字是否已经存在
        WineryNameReqVO wineryNameReqVO= new WineryNameReqVO();
        wineryNameReqVO.setWineryNameEng(wineryName);
        if (wineryService.queryByName(wineryNameReqVO)!=null){
            throw new BizException("winery already exists");
        }
    }



}
