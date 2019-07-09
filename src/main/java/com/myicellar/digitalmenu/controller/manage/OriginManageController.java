package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Origin;
import com.myicellar.digitalmenu.service.OriginService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.OriginDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.OriginReqVO;
import com.myicellar.digitalmenu.vo.request.OriginUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.OriginRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/origin")
@Api(tags = "产地", description = "/manage/origin")
public class OriginManageController {

    @Autowired
    private OriginService originService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<OriginRespVO>> queryList() {
        List<Origin> page = originService.queryList();

        PageResponseVO<OriginRespVO> resultPage = new PageResponseVO<OriginRespVO>();
        if(page!=null && page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
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
    public ResultVO<OriginRespVO> add(@RequestBody OriginReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Origin origin = ConvertUtils.convert(reqVO,Origin.class);
        origin.setWineOriginId(snowflakeIdWorker.nextId());

        Date now = new Date();

        origin.setUpdatedAt(now);
        int i = originService.insertSelective(origin);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        OriginRespVO respVO = ConvertUtils.convert(origin,OriginRespVO.class);
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
    public ResultVO update(@RequestBody OriginUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Origin origin = ConvertUtils.convert(reqVO,Origin.class);
        Date now = new Date();
        origin.setUpdatedAt(now);
        int i = originService.updateByPrimaryKeySelective(origin);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        OriginRespVO respVO = ConvertUtils.convert(origin,OriginRespVO.class);
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
    public ResultVO update(@RequestBody OriginDeleteReqVO reqVO) {
        if(reqVO.getWineOriginId()==null || reqVO.getWineOriginId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = originService.deleteByPrimaryKey(reqVO.getWineOriginId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(OriginReqVO reqVO){
        if(reqVO.getWineOriginId()==null || reqVO.getWineOriginId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getRegionNameEng())){
            throw new BizException("originNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(OriginUpdateReqVO reqVO){
        if(reqVO.getWineOriginId()==null || reqVO.getWineOriginId()==0L){
            throw new BizException("originId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getRegionNameEng())){
            throw new BizException("originNameEng cannot be empty!");
        }
    }

}
