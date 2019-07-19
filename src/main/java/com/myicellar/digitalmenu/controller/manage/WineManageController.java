package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/wine")
@Api(tags = "酒品管理", description = "/manage/wine")
public class WineManageController {

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
    public ResultVO<PageResponseVO<WineRespVO>> queryListPage(@RequestBody WinePageReqVO reqVO) {
        PageResponseVO<Wine> page = wineService.queryPageList(reqVO);

        PageResponseVO<WineRespVO> resultPage = new PageResponseVO<WineRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineRespVO.class);
        }

        return ResultVO.success(resultPage);
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByPrimaryKey")
    @AuthIgnore
    @ApiOperation("详情查询")
    public ResultVO<Wine> queryByPrimaryKey(@RequestBody WineDetailReqVO reqVO) {
        //通过传入wineId查询wine详情
        Wine wine = wineService.selectByPrimaryKey(reqVO.getWineId());
        if (wine!=null){
            return ResultVO.success(wine);
        }

        return ResultVO.validError("查询失败!");
    }

    /**
     * 下拉框查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryDropList")
    @AuthIgnore
    @ApiOperation("下拉框查询")
    public ResultVO<List<Wine>> queryDropList(@RequestBody WineTypeReqVO reqVO) {
        //通过传入wineId查询wine详情
        List<Wine> list = wineService.queryDropList(reqVO);
        if (list!=null){
            return ResultVO.success(list);
        }

        return ResultVO.validError("查询失败!");
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
    public ResultVO<WineRespVO> add(@RequestBody WineReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO,Wine.class);
        wine.setWineId(snowflakeIdWorker.nextId());
        wine.setUpdatedAt(new Date());
        int i = wineService.insertSelective(wine);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine,WineRespVO.class);
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
    public ResultVO update(@RequestBody WineUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Wine wine = ConvertUtils.convert(reqVO,Wine.class);
        wine.setUpdatedAt(new Date());
        int i = wineService.updateByPrimaryKeySelective(wine);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineRespVO respVO = ConvertUtils.convert(wine,WineRespVO.class);
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
    public ResultVO update(@RequestBody WineDeleteReqVO reqVO) {
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = wineService.deleteByPrimaryKey(reqVO.getWineId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wine cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getWineNameEng())){
            throw new BizException("wineNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineUpdateReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getWineNameEng())){
            throw new BizException("wineNameEng cannot be empty!");
        }
    }

}
