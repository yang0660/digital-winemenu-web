package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.WineAttr;
import com.myicellar.digitalmenu.service.WineAttrService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.WineAttrDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.WineAttrPageReqVO;
import com.myicellar.digitalmenu.vo.request.WineAttrReqVO;
import com.myicellar.digitalmenu.vo.request.WineAttrUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineAttrRespVO;
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
@RequestMapping("/manage/wineAttr")
@Api(tags = "酒品-配料/口味", description = "/manage/wineAttr")
public class WineAttrManageController {

    @Autowired
    private WineAttrService wineAttrService;
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
    public ResultVO<PageResponseVO<WineAttrRespVO>> queryListPage(@RequestBody WineAttrPageReqVO reqVO) {
        PageResponseVO<WineAttr> page = wineAttrService.queryPageList(reqVO);

        PageResponseVO<WineAttrRespVO> resultPage = new PageResponseVO<WineAttrRespVO>();
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            resultPage = ConvertUtils.convertPage(page,WineAttrRespVO.class);
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
    public ResultVO<WineAttrRespVO> add(@RequestBody WineAttrReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        WineAttr wineAttr = ConvertUtils.convert(reqVO,WineAttr.class);
        wineAttr.setWineId(snowflakeIdWorker.nextId());

        Date now = new Date();

        wineAttr.setUpdatedAt(now);
        int i = wineAttrService.insertSelective(wineAttr);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        WineAttrRespVO respVO = ConvertUtils.convert(wineAttr,WineAttrRespVO.class);
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
    public ResultVO update(@RequestBody WineAttrUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        WineAttr wineAttr = ConvertUtils.convert(reqVO,WineAttr.class);
        Date now = new Date();
        wineAttr.setUpdatedAt(now);
        int i = wineAttrService.updateByPrimaryKeySelective(wineAttr);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        WineAttrRespVO respVO = ConvertUtils.convert(wineAttr,WineAttrRespVO.class);
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
    public ResultVO update(@RequestBody WineAttrDeleteReqVO reqVO) {
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = wineAttrService.deleteByPrimaryKey(reqVO.getWineId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(WineAttrReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("supplier cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(WineAttrUpdateReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineAttrId cannot be empty!");
        }

    }

}
