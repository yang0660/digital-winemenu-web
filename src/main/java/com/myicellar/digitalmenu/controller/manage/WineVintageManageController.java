package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.service.WineVintageService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineVintageDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintagePageReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageReqVO;
import com.myicellar.digitalmenu.vo.request.WineVintageUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineVintageRespVO;
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
@RequestMapping("/manage/wineVintage")
@Api(tags = "年份", description = "/manage/wineVintage")
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
    public ResultVO<PageResponseVO<WineVintageRespVO>> queryListPage(@RequestBody WineVintagePageReqVO reqVO) {
        PageResponseVO<WineVintage> page = wineVintageService.queryPageList(reqVO);

        PageResponseVO<WineVintageRespVO> resultPage = new PageResponseVO<WineVintageRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineVintageRespVO.class);
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
    public ResultVO<WineVintageRespVO> add(@RequestBody WineVintageReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        WineVintage wineVintage = ConvertUtils.convert(reqVO,WineVintage.class);
        wineVintage.setWineId(snowflakeIdWorker.nextId());

        Date now = new Date();

        wineVintage.setUpdatedAt(now);
        int i = wineVintageService.insertSelective(wineVintage);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineVintageRespVO respVO = ConvertUtils.convert(wineVintage,WineVintageRespVO.class);
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
    public ResultVO update(@RequestBody WineVintageUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        WineVintage wineVintage = ConvertUtils.convert(reqVO,WineVintage.class);
        Date now = new Date();
        wineVintage.setUpdatedAt(now);
        int i = wineVintageService.updateByPrimaryKeySelective(wineVintage);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineVintageRespVO respVO = ConvertUtils.convert(wineVintage,WineVintageRespVO.class);
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
    public ResultVO update(@RequestBody WineVintageDeleteReqVO reqVO) {
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = wineVintageService.deleteByPrimaryKey(reqVO.getWineId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineVintageReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("supplier cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineVintageUpdateReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineVintageId cannot be empty!");
        }

    }

}
