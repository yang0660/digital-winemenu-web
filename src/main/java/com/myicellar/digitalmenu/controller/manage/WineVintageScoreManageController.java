package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.service.WineVintageScoreService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineVintageScoreDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageScorePageReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageScoreReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageScoreUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineVintageScoreRespVO;
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

@RestController
@Slf4j
@RequestMapping("/manage/wineVintageScore")
@Api(tags = "获奖信息", description = "/manage/wineVintageScore")
public class WineVintageScoreManageController {

    @Autowired
    private WineVintageScoreService wineVintageScoreService;
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
    public ResultVO<PageResponseVO<WineVintageScoreRespVO>> queryListPage(@RequestBody WineVintageScorePageReqVO reqVO) {
        PageResponseVO<WineVintageScore> page = wineVintageScoreService.queryPageList(reqVO);

        PageResponseVO<WineVintageScoreRespVO> resultPage = new PageResponseVO<WineVintageScoreRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineVintageScoreRespVO.class);
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
    public ResultVO<WineVintageScoreRespVO> add(@RequestBody WineVintageScoreReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        WineVintageScore wineVintageScore = ConvertUtils.convert(reqVO,WineVintageScore.class);
        wineVintageScore.setWineId(snowflakeIdWorker.nextId());

        Date now = new Date();

        wineVintageScore.setUpdatedAt(now);
        int i = wineVintageScoreService.insertSelective(wineVintageScore);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineVintageScoreRespVO respVO = ConvertUtils.convert(wineVintageScore,WineVintageScoreRespVO.class);
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
    public ResultVO update(@RequestBody WineVintageScoreUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        WineVintageScore wineVintageScore = ConvertUtils.convert(reqVO,WineVintageScore.class);
        Date now = new Date();
        wineVintageScore.setUpdatedAt(now);
        int i = wineVintageScoreService.updateByPrimaryKeySelective(wineVintageScore);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineVintageScoreRespVO respVO = ConvertUtils.convert(wineVintageScore,WineVintageScoreRespVO.class);
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
    public ResultVO update(@RequestBody WineVintageScoreDeleteReqVO reqVO) {
        if(reqVO.getVintageScoreId()==null || reqVO.getVintageScoreId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = wineVintageScoreService.deleteByPrimaryKey(reqVO.getVintageScoreId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineVintageScoreReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("supplier cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineVintageScoreUpdateReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineVintageScoreId cannot be empty!");
        }

    }

}
