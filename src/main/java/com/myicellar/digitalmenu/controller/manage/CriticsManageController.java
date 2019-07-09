package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Critics;
import com.myicellar.digitalmenu.service.CriticsService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.CriticsDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.CriticsReqVO;
import com.myicellar.digitalmenu.vo.request.CriticsUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.CriticsRespVO;
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
@RequestMapping("/manage/critics")
@Api(tags = "评价", description = "/manage/critics")
public class CriticsManageController {

    @Autowired
    private CriticsService criticsService;
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
    public ResultVO<List<CriticsRespVO>> queryList() {
        List<Critics> page = criticsService.queryList();

        PageResponseVO<CriticsRespVO> resultPage = new PageResponseVO<CriticsRespVO>();
        if(page!=null && !page.isEmpty()){
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
    public ResultVO<CriticsRespVO> add(@RequestBody CriticsReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Critics critics = ConvertUtils.convert(reqVO,Critics.class);
        critics.setWineCriticsId(snowflakeIdWorker.nextId());

        Date now = new Date();

        critics.setUpdatedAt(now);
        int i = criticsService.insertSelective(critics);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        CriticsRespVO respVO = ConvertUtils.convert(critics,CriticsRespVO.class);
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
    public ResultVO update(@RequestBody CriticsUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Critics critics = ConvertUtils.convert(reqVO,Critics.class);
        Date now = new Date();
        critics.setUpdatedAt(now);
        int i = criticsService.updateByPrimaryKeySelective(critics);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        CriticsRespVO respVO = ConvertUtils.convert(critics,CriticsRespVO.class);
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
    public ResultVO update(@RequestBody CriticsDeleteReqVO reqVO) {
        if(reqVO.getCriticsId()==null || reqVO.getCriticsId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = criticsService.deleteByPrimaryKey(reqVO.getCriticsId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(CriticsReqVO reqVO){
        if(reqVO.getWineCriticsId()==null || reqVO.getWineCriticsId()==0L){
            throw new BizException("supplier cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(CriticsUpdateReqVO reqVO){
        if(reqVO.getWineCriticsId()==null || reqVO.getWineCriticsId()==0L){
            throw new BizException("criticsId cannot be empty!");
        }

    }

}
